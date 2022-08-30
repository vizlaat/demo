package com.example;

import org.junit.jupiter.api.*;
import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;

/**
 * For testing purposes, the next temporary structure is used (D: expected deletion).
 * doOr/
 * 	L flooR/
 * 		L aBc.doc
 * 		L aBc.bak
 * D	L Cba.bak
 * 		L ssS.doc
 * 		L Sss.bak
 * 		L teapot.xml
 * D	L questioN/
 * D		L Answer/
 * D		L Quest/
 * 		L ceiLing/
 * D		L carpeT/
 * D			L xyz.bak
 * 			L paintinG/
 * 				L aaa.xml
 */
class CleanFoldersTest {
	private static final String fileSeparator = File.separator;
	private static final String root = (new File("src" + fileSeparator + "test" + fileSeparator + "resources")).getAbsolutePath() + fileSeparator;
	private static List<File> folders;
	private static List<File> files;

	static File door, floor, ceiling, abc_doc, abc_bak, cba_bak, sss_doc, sss_bak, teapot_xml;
	static File question, answer, quest, carpet, painting, xyz_bak, aaa_xml;

	@BeforeAll
	static void setUp() {
		door = new File(root + "doOr");
		floor = new File(root + "doOr" + fileSeparator + "flooR");
		question = new File(root + "doOr" + fileSeparator + "flooR" + fileSeparator + "questioN");
		answer = new File(root + "doOr" + fileSeparator + "flooR" + fileSeparator + "Answer");
		quest = new File(root + "doOr" + fileSeparator + "flooR" + fileSeparator + "Answer" + fileSeparator + "Quest");
		ceiling = new File(root + "doOr" + fileSeparator + "ceiLing");
		carpet = new File(root + "doOr" + fileSeparator + "ceiLing" + fileSeparator + "carpeT");
		painting = new File(root + "doOr" + fileSeparator + "ceiLing" + fileSeparator + "paintinG");
		folders = new ArrayList<>(Arrays.asList(door, floor, question, answer, quest, ceiling, carpet, painting));
		int i = 0;
		try {
			while (i < folders.size()) {
				if (!folders.get(i).mkdir()) {
					System.out.println("Failed to make directory: " + folders.get(i).getPath());
				}
				i++;
			}
		}
		catch (Exception e) {
			System.out.println("Making directory threw exception: " + folders.get(i));
			assert false;
		}
		abc_doc =  new File(root + "doOr" + fileSeparator + "flooR" + fileSeparator + "aBc.doc");
		abc_bak =  new File(root + "doOr" + fileSeparator + "flooR" + fileSeparator + "aBc.bak");
		cba_bak = new File(root + "doOr" + fileSeparator + "flooR" + fileSeparator + "Cba.bak");
		sss_doc = new File(root + "doOr" + fileSeparator + "flooR" + fileSeparator + "ssS.doc");
		sss_bak = new File(root + "doOr" + fileSeparator + "flooR" + fileSeparator + "Sss.bak");
		teapot_xml = new File(root + "doOr" + fileSeparator + "flooR" + fileSeparator + "teapot.xml");
		xyz_bak = new File(root + "doOr" + fileSeparator + "ceiLing" + fileSeparator + "carpeT" + fileSeparator + "xyz.bak");
		aaa_xml = new File(root + "doOr" + fileSeparator + "ceiLing" + fileSeparator + "paintinG" + fileSeparator + "aaa.xml");
		files = new ArrayList<>(Arrays.asList(abc_doc, abc_bak, cba_bak, sss_doc, sss_bak, teapot_xml, xyz_bak, aaa_xml));
		i = 0;
		try {
			while (i < files.size()) {
				if (!files.get(i).createNewFile()) {
					System.out.println("Failed to make file: " + files.get(i).getPath());
				}
				i++;
			}
		}
		catch (Exception e) {
			System.out.println("Making file threw exception: " + files.get(i));
			assert false;
		}
	}

	@AfterAll
	static void tearDown() {
		int i = 0;
		try {
			while (i < files.size()) {
				files.get(i).delete();
				i++;
			}
		}
		catch (Exception e) {
			System.out.println("File deletion threw exception: " + files.get(i));
			assert false;
		}
		i = folders.size() - 1;
		try {
			while (i > -1) {
				folders.get(i).delete();
				i--;
			}
		}
		catch (Exception e) {
			System.out.println("Directory deletion threw exception: " + folders.get(i));
			assert false;
		}
	}

	@Test
	void cleanFoldersTest() {
		CleanFolders.cleanFolders(door);

/*
		List<File> foldersExpected = Arrays.asList(floor, ceiling, question, answer, quest, carpet, painting);
		foldersExpected.sort(Comparator.naturalOrder());
		List<File> foldersActual = CleanFolders.getFolders();
		foldersActual.sort(Comparator.naturalOrder());
		Assertions.assertEquals(foldersExpected, foldersActual);

		List<File> filesExpected = Arrays.asList(cba_bak, xyz_bak);
		filesExpected.sort(Comparator.naturalOrder());
		List<File> filesActual = CleanFolders.getDeletableBaks();
		filesActual.sort(Comparator.naturalOrder());
		Assertions.assertEquals(filesExpected, filesActual);
*/
		List<File> expectedCollectedDeletableFiles = Arrays.asList(cba_bak, xyz_bak);
		expectedCollectedDeletableFiles.sort(Comparator.naturalOrder());
		System.out.println("Expected: " + expectedCollectedDeletableFiles.size());
		List<File> actualCollectedDeletableFiles = CleanFolders.cleanFolders(door);
		actualCollectedDeletableFiles.sort(Comparator.naturalOrder());
		System.out.println("Actual: " + actualCollectedDeletableFiles.size());
		Assertions.assertEquals(expectedCollectedDeletableFiles, actualCollectedDeletableFiles);
		assert door.exists();
		assert floor.exists();
		assert abc_doc.exists();
		assert abc_bak.exists();
		assert !cba_bak.exists();
		assert sss_doc.exists();
		assert sss_bak.exists();
		assert teapot_xml.exists();
		assert !question.exists();
		assert !answer.exists();
		assert !quest.exists();
		assert ceiling.exists();
		assert !carpet.exists();
		assert !xyz_bak.exists();
		assert painting.exists();
		assert aaa_xml.exists();
	}
}