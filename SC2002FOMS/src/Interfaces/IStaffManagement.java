package Interfaces;
import java.io.IOException;
/**
 * The {@code IStaffManagement} interface includes methods to manage staff accounts and activities.
 * Capabilities include editing, filtering, promoting, and transferring staff, with potential for I/O exceptions.
 */

public interface IStaffManagement {
    void editStaff() throws IOException;
    void filterStaff() throws IOException;
    void promoteStaff() throws IOException;
    void transferStaff() throws IOException;
}
