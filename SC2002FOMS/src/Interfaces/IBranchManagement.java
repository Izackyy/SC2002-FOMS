package Interfaces;
import java.io.IOException;

public interface IBranchManagement {
    void setBranchStatus() throws IOException;
    void removeBranch() throws IOException;
    void addBranch() throws IOException;
}
