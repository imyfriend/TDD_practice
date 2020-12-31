package com.example.tdd;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

/*
该rule能够创建文件以及文件夹，并且在测试方法结束的时候自动删除掉创建的文件，无论测试通过或者失败。
 */
public class TemporaryFolderTest {
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    private static File file;

    @Before
    public void setUp() throws IOException {
        file = folder.newFile("test.txt");
    }

    @Test
    public void testFileCreation() throws IOException {
        System.out.println("testFileCreation file exists : " + file.exists());
    }

    @After
    public void tearDown() {
        System.out.println("tearDown file exists : " + file.exists());
    }

    @AfterClass
    public static void finish() {
        System.out.println("finish file exists : " + file.exists());
    }
}
