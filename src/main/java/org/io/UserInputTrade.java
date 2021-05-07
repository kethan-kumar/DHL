/* @Author: Yash Jaiswal */

package org.io;

import org.io.interfaces.IUserInputTrade;

import java.util.Scanner;

public class UserInputTrade implements IUserInputTrade {

    public Integer userInputInt() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
