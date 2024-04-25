package Interfaces;
import java.io.IOException;

public interface IBranchManagement {
    void addBranch() throws IOException;
    void removeBranch() throws IOException;
    void filterBranch() throws IOException;
}
