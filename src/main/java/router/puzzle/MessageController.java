package router.puzzle;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageController {

    private static final Logger LOGGER = Logger.getLogger(MessageController.class.getName());

    /**
     * This function is calculated using loop as there is no direct formula to get 'n'th term, subsequent term is
     * based on previous term.
     *
     * @param r number of routers between two systems
     * @param b number of bits per packet
     * @param i number of bits in the meaningful information that system wants to transmit
     * @return number of minimum bits required to transmit the required information
     */
    public int getNumberOfTotalBitsToSendInfo(int r, int b, int i) {
        int numberOfTotalBitsToSendInfo = i;
        for (int j = 1; j <= r; j++) {
            int previousPacketCount = getPacketCount(numberOfTotalBitsToSendInfo, b);
            numberOfTotalBitsToSendInfo += previousPacketCount;
            int currentPacketCount = getPacketCount(numberOfTotalBitsToSendInfo, b);
            if (currentPacketCount > previousPacketCount) {
                ++numberOfTotalBitsToSendInfo;
            }
            LOGGER.log(Level.INFO, "Bits at router {0} are ", numberOfTotalBitsToSendInfo);
        }
        return numberOfTotalBitsToSendInfo;
    }

    /**
     * @param r number of routers between two systems
     * @param b number of bits per packet
     * @param i number of bits in the meaningful information that system wants to transmit
     * @return number of minimum packets required to transmit the required information
     */
    public int getNumberOfPackets(int r, int b, int i) {
        return getPacketCount(getNumberOfTotalBitsToSendInfo(r, b, i), b);
    }

    /**
     * @param r number of routers between two systems
     * @param b number of bits per packet
     * @param n number of packets
     * @return maximum number of bits of meaningful information can be sent
     */
    public int getNumberOfInfoBitsForGivenPacketNumber(int r, int b, int n) {
        return getNumberOfInfoBitsForGivenNumberOfBits(r, b, n * b);
    }

    /**
     * @param r             number of routers between two systems
     * @param b             number of bits per packet
     * @param totalBitsUsed sum of actual bits used from given packets
     * @return maximum number of bits of meaningful information can be sent
     */
    public int getNumberOfInfoBitsForGivenNumberOfBits(int r, int b, int totalBitsUsed) {
        if (r == 0) {
            return totalBitsUsed;
        } else {
            int numberOfBitsStrippedAtCurrentRouter = getPacketCount(totalBitsUsed, b);

            LOGGER.log(Level.INFO, "Bits at router " + r + " are " + totalBitsUsed + " stripping " + numberOfBitsStrippedAtCurrentRouter + " bits");

            int bitsUsedForNextRouter = totalBitsUsed - numberOfBitsStrippedAtCurrentRouter;
            return getNumberOfInfoBitsForGivenNumberOfBits(r - 1, b, bitsUsedForNextRouter);
        }
    }

    /**
     *
     * Util function
     *
     * @param totalNumberOfBits
     * @param bitsPerPacket
     * @return
     */
    private int getPacketCount(int totalNumberOfBits, int bitsPerPacket) {
        return ((totalNumberOfBits / bitsPerPacket) + (totalNumberOfBits % bitsPerPacket == 0 ? 0 : 1));
    }
}

