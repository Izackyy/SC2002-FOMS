package Interfaces;
import java.io.IOException;

public interface IStaffManagement {
    void editStaff() throws IOException;
    void filterStaff() throws IOException;
    void promoteStaff() throws IOException;
    void transferStaff() throws IOException;
}
