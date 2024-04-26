package Interfaces;

import java.io.IOException;
/**
 * The {@code IBranchManagement} interface provides methods for managing branches.
 * It supports adding, setting the status of, removing, and printing branch details to a file,
 * with each method capable of throwing an {@code IOException} due to I/O operations.
 */
public interface IBranchManagement {

    void addBranch() throws IOException;

    void setBranchStatus() throws IOException;

    void removeBranch() throws IOException;

    void printBranch(String filename) throws IOException;

}

