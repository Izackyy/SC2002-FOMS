package Interfaces;
import java.io.IOException;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
/**
 * The {@code IStaffManagement} interface includes methods to manage staff accounts and activities.
 * Capabilities include editing, filtering, promoting, and transferring staff, with potential for I/O exceptions.
 */

public interface IStaffManagement {
    void addStaffAcc() throws IOException;
    void removeStaffAcc() throws IOException;
    void editStaffAcc() throws IOException;
    void filterStaff() throws IOException;
    void promoteStaff() throws IOException;
    void transferStaff() throws IOException;
}
