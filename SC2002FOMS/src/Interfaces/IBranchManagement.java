package Interfaces;

import java.io.IOException;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
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

