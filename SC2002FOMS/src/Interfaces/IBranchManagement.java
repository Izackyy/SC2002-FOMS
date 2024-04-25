package Interfaces;
import java.io.IOException;

public interface IBranchManagement {
    void addBranch() throws IOException;
    void editBranch() throws IOException;
    void deleteBranch() throws IOException;
    void viewBranch() throws IOException;
    void filterBranch() throws IOException;
}
