package com.example.demo.parser;

import com.example.demo.data.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BankXmlParser extends DefaultHandler {
    BICDirectoryEntry.Bulldozer currentEntryBuilder;
    ParticipantInfo.Bulldozer participantBuilder;
    Account.Builder accountBuilder;

    public EntryParsed onEntryParsed;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        super.startPrefixMapping(prefix, uri);
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        super.endPrefixMapping(prefix);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
        super.startElement(uri, localName, qName, attrs);

        switch (qName) {
            case "BICDirectoryEntry" -> bicDirectoryEntryStart(attrs);
            case "ParticipantInfo" -> participantInfoStart(attrs);
            case "RstrList" -> restrictionStart(attrs);
            case "SWBICS" -> swbicsStart(attrs);
            case "Accounts" -> accountStart(attrs);
            case "AccRstrList" -> accountRestrictionStart(attrs);
        }
    }

    void bicDirectoryEntryStart(Attributes attributes) {

        String bic = attributes.getValue("BIC");
        String changeType = attributes.getValue("chgTp");

        currentEntryBuilder = BICDirectoryEntry.builder();
        currentEntryBuilder.bic(BIC.from(bic));
        if (changeType != null) {
            currentEntryBuilder.changeType(ChangeType.from(changeType));
        }
    }

    void participantInfoStart(Attributes attributes) {
        participantBuilder = ParticipantInfo.builder()
                .russianName(attributes.getValue("NameP"))
                .englishName(attributes.getValue("EnglName"))
                .regN(attributes.getValue("RegN"))
                .countryCode(attributes.getValue("CntrCd"))
                .region(attributes.getValue("Rgn"))
                .ind(attributes.getValue("Ind"))
                .tnp(attributes.getValue("Tnp"))
                .nnp(attributes.getValue("Nnp"))
                .adr(attributes.getValue("Adr"))
                .prntBIC(BIC.from(attributes.getValue("PrntBIC")))
                .dateIn(DateString.from(attributes.getValue("DateIn")))
                .dateOut(DateString.from(attributes.getValue("DateOut")))
                .ptType(attributes.getValue("PtType"))
                .svrvcs(attributes.getValue("Srvcs"))
                .xchType(attributes.getValue("XchType"))
                .uid(UID.from(attributes.getValue("UID")))
                .participantStatus(attributes.getValue("ParticipantStatus"))
                .ptType(attributes.getValue("PtType"));
    }


    void restrictionStart(Attributes attributes) {
        participantBuilder.restriction(
                new Restriction(
                        attributes.getValue("Rstr"),
                        DateString.from(attributes.getValue("RstrDate")))
        );
    }

    void swbicsStart(Attributes attrs) {
        currentEntryBuilder.swbic(new SWBIC(attrs.getValue("SWBIC"), attrs.getValue("DefaultSWBIC")));
    }

    void accountStart(Attributes attrs) {
        accountBuilder = Account.builder()
                .account(attrs.getValue("Account"))
                .regulationAccountType(attrs.getValue("RegulationAccountType"))
                .ck((attrs.getValue("CK")))
                .accountCBRBIC(BIC.from(attrs.getValue("AccountCBRBIC")))
                .dateIn(DateString.from(attrs.getValue("DateIn")))
                .dateOut(DateString.from(attrs.getValue("DateOut")))
                .accountStatus(attrs.getValue("AccountStatus"));
    }

    void accountRestrictionStart(Attributes attrs) {
        accountBuilder.accountRestriction(new AccountRestriction(
                attrs.getValue("AccRstr"),
                DateString.from(attrs.getValue("AccRstrDate")),
                BIC.from(attrs.getValue("SuccessorBIC"))
        ));
    }

    void accountEnd() {
        currentEntryBuilder.account(accountBuilder.build());
    }

    void participantInfoEnd() {
        currentEntryBuilder.participantInfo(participantBuilder.build());
    }

    void bicDirectoryEntryEnd() {
        onEntryParsed.entryParsed(currentEntryBuilder.build());

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch (qName) {
            case "BICDirectoryEntry" -> bicDirectoryEntryEnd();
            case "ParticipantInfo" -> participantInfoEnd();
            case "Accounts" -> accountEnd();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }
}
