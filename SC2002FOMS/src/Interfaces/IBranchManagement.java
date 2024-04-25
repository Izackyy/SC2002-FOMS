package Interfaces;

import java.io.IOException;

public interface IBranchManagement {
    void addBranch() throws IOException;

    void setBranchStatus() throws IOException;

    void removeBranch() throws IOException;

    void viewBranch() throws IOException;

    void filterBranch() throws IOException;
}
