package router.puzzle;

import static org.junit.jupiter.api.Assertions.*;

class MessageControllerTest extends MessageController {

    @org.junit.jupiter.api.Test
    void getNumberOfTotalBitsToSendInfo() {
        assertEquals(90, getNumberOfTotalBitsToSendInfo(30, 30, 25));
        assertEquals(234, getNumberOfTotalBitsToSendInfo(30, 40, 100));
    }

    @org.junit.jupiter.api.Test
    void getNumberOfPackets() {
        assertEquals(3, getNumberOfPackets(30, 30, 25));
    }

    @org.junit.jupiter.api.Test
    void getNumberOfInfoBitsForGivenPacketNumber() {
        assertEquals(25, getNumberOfInfoBitsForGivenNumberOfBits(30, 30, 90));
        assertEquals(25, getNumberOfInfoBitsForGivenPacketNumber(30, 30, 3));
        assertEquals(100, getNumberOfInfoBitsForGivenNumberOfBits(30, 40, 234));
    }

    @org.junit.jupiter.api.Test
    void check() {
        assertEquals(234, getNumberOfTotalBitsToSendInfo(30, 40, 100));
        assertEquals(100, getNumberOfInfoBitsForGivenNumberOfBits(30, 40, 234));
    }


}