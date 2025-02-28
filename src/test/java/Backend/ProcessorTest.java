package Backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorTest {

    @Test
    void retreiveAllData() {

        String dataJSON = "{\"type\": \"MX\", \"@xmlns\": \"urn:iso:std:iso:20022:tech:xsd:pacs.009.001.08\", \"appHdr\": {\"fr\": {\"fiId\": {\"finInstnId\": {\"bicfi\": \"ASPXIDJAXXX\"}}}, \"to\": {\"fiId\": {\"finInstnId\": {\"bicfi\": \"MUABIDJAXXX\"}}}, \"prty\": \"NORM\", \"creDt\": {\"day\": 31, \"hour\": 0, \"year\": 2021, \"month\": 12, \"minute\": 0, \"second\": 0, \"timezone\": 0}, \"bizSvc\": \"swift.cbprplus.adv.02\", \"bizMsgIdr\": \"F5S1905987123493\", \"msgDefIdr\": \"pacs.009.001.08\", \"namespace\": \"urn:iso:std:iso:20022:tech:xsd:head.001.001.02\"}, \"fiCdtTrf\": {\"grpHdr\": {\"msgId\": \"F5S1905987123493\", \"creDtTm\": {\"day\": 31, \"hour\": 0, \"year\": 2021, \"month\": 12, \"minute\": 0, \"second\": 0, \"timezone\": 0}, \"nbOfTxs\": \"1\", \"sttlmInf\": {\"sttlmMtd\": \"COVE\", \"instgRmbrsmntAgt\": {\"finInstnId\": {\"bicfi\": \"IRVTUS3N\"}}}}, \"cdtTrfTxInf\": [{\"dbtr\": {\"finInstnId\": {\"bicfi\": \"ASPXIDJAXXX\"}}, \"pmtId\": {\"uetr\": \"671b60fa-a8a3-4603-afc3-2e5f0b8152ab\", \"instrId\": \"F5S1905987123493\", \"endToEndId\": \"RELATED123REFF\"}, \"cdtrAgt\": {\"finInstnId\": {\"bicfi\": \"PNBPUS33NYC\"}}, \"instdAgt\": {\"finInstnId\": {\"bicfi\": \"MUABIDJAXXX\"}}, \"instgAgt\": {\"finInstnId\": {\"bicfi\": \"MUABIDJAXXX\"}}, \"intrmyAgt1\": {\"finInstnId\": {\"bicfi\": \"PNBPUS3NNYC\"}}, \"intrBkSttlmDt\": {\"day\": 18, \"hour\": -2147483648, \"year\": 2025, \"month\": 2, \"minute\": -2147483648, \"second\": -2147483648, \"timezone\": -2147483648}, \"instrForNxtAgt\": [{\"instrInf\": \"/REC/PLS DEBIT OUR AC.79827131`2 WITH\"}, {\"instrInf\": \" ORFAS BANK\"}], \"intrBkSttlmAmt\": {\"ccy\": \"JPY\", \"value\": 4000}, \"instrForCdtrAgt\": [{\"instrInf\": \"12345678B2\"}]}]}, \"identifier\": \"pacs.009.001.08\"}";
        Processor processor = new Processor();
        processor.setSep("==");
        processor.retreiveAllData("fiCdtTrf",dataJSON,"|");
        System.out.println(processor.getBuilder());
    }

    @Test
    void retreiveDeepAllData() {

        String dataJSON = "{\"type\": \"MX\", \"@xmlns\": \"urn:iso:std:iso:20022:tech:xsd:pacs.009.001.08\", \"appHdr\": {\"fr\": {\"fiId\": {\"finInstnId\": {\"bicfi\": \"ASPXIDJAXXX\"}}}, \"to\": {\"fiId\": {\"finInstnId\": {\"bicfi\": \"MUABIDJAXXX\"}}}, \"prty\": \"NORM\", \"creDt\": {\"day\": 31, \"hour\": 0, \"year\": 2021, \"month\": 12, \"minute\": 0, \"second\": 0, \"timezone\": 0}, \"bizSvc\": \"swift.cbprplus.adv.02\", \"bizMsgIdr\": \"F5S1905987123493\", \"msgDefIdr\": \"pacs.009.001.08\", \"namespace\": \"urn:iso:std:iso:20022:tech:xsd:head.001.001.02\"}, \"fiCdtTrf\": {\"grpHdr\": {\"msgId\": \"F5S1905987123493\", \"creDtTm\": {\"day\": 31, \"hour\": 0, \"year\": 2021, \"month\": 12, \"minute\": 0, \"second\": 0, \"timezone\": 0}, \"nbOfTxs\": \"1\", \"sttlmInf\": {\"sttlmMtd\": \"COVE\", \"instgRmbrsmntAgt\": {\"finInstnId\": {\"bicfi\": \"IRVTUS3N\"}}}}, \"cdtTrfTxInf\": [{\"dbtr\": {\"finInstnId\": {\"bicfi\": \"ASPXIDJAXXX\"}}, \"pmtId\": {\"uetr\": \"671b60fa-a8a3-4603-afc3-2e5f0b8152ab\", \"instrId\": \"F5S1905987123493\", \"endToEndId\": \"RELATED123REFF\"}, \"cdtrAgt\": {\"finInstnId\": {\"bicfi\": \"PNBPUS33NYC\"}}, \"instdAgt\": {\"finInstnId\": {\"bicfi\": \"MUABIDJAXXX\"}}, \"instgAgt\": {\"finInstnId\": {\"bicfi\": \"MUABIDJAXXX\"}}, \"intrmyAgt1\": {\"finInstnId\": {\"bicfi\": \"PNBPUS3NNYC\"}}, \"intrBkSttlmDt\": {\"day\": 18, \"hour\": -2147483648, \"year\": 2025, \"month\": 2, \"minute\": -2147483648, \"second\": -2147483648, \"timezone\": -2147483648}, \"instrForNxtAgt\": [{\"instrInf\": \"/REC/PLS DEBIT OUR AC.79827131`2 WITH\"}, {\"instrInf\": \" ORFAS BANK\"}], \"intrBkSttlmAmt\": {\"ccy\": \"JPY\", \"value\": 4000}, \"instrForCdtrAgt\": [{\"instrInf\": \"12345678B2\"}]}]}, \"identifier\": \"pacs.009.001.08\"}";

        Processor processor = new Processor();
//        processor.setSep("/");

        processor.setSep("==");
        processor.retreiveAllData("fiCdtTrf#cdtTrfTxInf",dataJSON,"|");


        System.out.println(processor.getBuilder());
    }
}