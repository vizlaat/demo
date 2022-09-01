package com.example;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
 * 	L ceiLing/
 * D	L carpeT/
 * D		L xyz.bak
 * 		L paintinG/
 * 			L aaa.xml
 */
class CleanFoldersTest {
	private static final String separatorInPath = File.separator;
	private static final String root = (new File("src" + separatorInPath + "test" + separatorInPath + "resources")).getAbsolutePath() + separatorInPath;
	private static List<File> folders;
	private static List<File> files;

	static File door, floor, ceiling, abc_doc, abc_bak, cba_bak, sss_doc, sss_bak, teapot_xml;
	static File question, answer, quest, carpet, painting, xyz_bak, aaa_xml;

	@BeforeAll
	static void setUp() {
		door = new File(root + "doOr");
		floor = new File(root + "doOr" + separatorInPath + "flooR");
		question = new File(root + "doOr" + separatorInPath + "flooR" + separatorInPath + "questioN");
		answer = new File(root + "doOr" + separatorInPath + "flooR" + separatorInPath + "Answer");
		quest = new File(root + "doOr" + separatorInPath + "flooR" + separatorInPath + "Answer" + separatorInPath + "Quest");
		ceiling = new File(root + "doOr" + separatorInPath + "ceiLing");
		carpet = new File(root + "doOr" + separatorInPath + "ceiLing" + separatorInPath + "carpeT");
		painting = new File(root + "doOr" + separatorInPath + "ceiLing" + separatorInPath + "paintinG");
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
		abc_doc =  new File(root + "doOr" + separatorInPath + "flooR" + separatorInPath + "aBc.doc");
		abc_bak =  new File(root + "doOr" + separatorInPath + "flooR" + separatorInPath + "aBc.bak");
		cba_bak = new File(root + "doOr" + separatorInPath + "flooR" + separatorInPath + "Cba.bak");
		sss_doc = new File(root + "doOr" + separatorInPath + "flooR" + separatorInPath + "ssS.doc");
		sss_bak = new File(root + "doOr" + separatorInPath + "flooR" + separatorInPath + "Sss.bak");
		teapot_xml = new File(root + "doOr" + separatorInPath + "flooR" + separatorInPath + "teapot.xml");
		xyz_bak = new File(root + "doOr" + separatorInPath + "ceiLing" + separatorInPath + "carpeT" + separatorInPath + "xyz.bak");
		aaa_xml = new File(root + "doOr" + separatorInPath + "ceiLing" + separatorInPath + "paintinG" + separatorInPath + "aaa.xml");
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
		List<File> expectedCollectedDeletableFiles = Arrays.asList(cba_bak, xyz_bak);
		expectedCollectedDeletableFiles.sort(Comparator.naturalOrder());
		System.out.println("Expected: " + expectedCollectedDeletableFiles.size());
		for (File f : expectedCollectedDeletableFiles) {
			System.out.println(f.getPath());
		}
		List<File> actualCollectedDeletableFiles = CleanFolders.cleanFolders(door);
		actualCollectedDeletableFiles.sort(Comparator.naturalOrder());
		System.out.println("Actual: " + actualCollectedDeletableFiles.size());
		for (File f : actualCollectedDeletableFiles) {
			System.out.println(f.getPath());
		}
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


	/*
	Tested structure:
	-entryPoint
		|-randomTextFile
	 */
	@Test
	public void nothingToClear() {
		File randomTextFile = Mockito.mock(File.class);
		when(randomTextFile.isDirectory()).thenReturn(false);
		when(randomTextFile.isFile()).thenReturn(true);
		when(randomTextFile.getPath()).thenReturn("random-text");

		File entryPoint = Mockito.mock(File.class);
		when(entryPoint.getPath()).thenReturn("random-folder");
		when(entryPoint.isDirectory()).thenReturn(true);
		when(entryPoint.isFile()).thenReturn(false);
		when(entryPoint.listFiles()).thenReturn(new File[]{randomTextFile});

		List<File> deletableBaks = CleanFolders.cleanFolders(entryPoint);
		Assertions.assertEquals(0, deletableBaks.size());
	}

	/*
	Tested structure:
	-topLevelDir
		|-whatever.bak
	 */
	@Test
	public void OneLoneBakInTheTopLevel() {
		File bakFile = Mockito.mock(File.class);
		when(bakFile.isDirectory()).thenReturn(false);
		when(bakFile.isFile()).thenReturn(true);
		when(bakFile.getPath()).thenReturn("whatever.bak");

		File topLevelDir = Mockito.mock(File.class);
		when(topLevelDir.isDirectory()).thenReturn(true);
		when(topLevelDir.isFile()).thenReturn(false);
		when(topLevelDir.getPath()).thenReturn("whateverfolder");
		when(topLevelDir.listFiles()).thenReturn(new File[]{bakFile});

		List<File> deletableBaks = CleanFolders.cleanFolders(topLevelDir);
		Assertions.assertEquals(1, deletableBaks.size());

		Mockito.verify(bakFile, Mockito.times(1)).delete();
		Mockito.verify(topLevelDir, Mockito.never()).delete();
	}

	/*
		Tested structure:
		-topLevelDir
			|-directory
		    	|-whatever.bak
 	*/
	@Test
	public void oneDirectoryOneLoneBak() {
		File bakFile = Mockito.mock(File.class);
		when(bakFile.isDirectory()).thenReturn(false);
		when(bakFile.isFile()).thenReturn(true);
		when(bakFile.getPath()).thenReturn("whatever.bak");

		File directory = Mockito.mock(File.class);
		when(directory.isDirectory()).thenReturn(true);
		when(directory.isFile()).thenReturn(false);
		when(directory.listFiles()).thenReturn(new File[]{bakFile});
		when(directory.getPath()).thenReturn("directory_contains_bak");

		File entryPoint = Mockito.mock(File.class);
		when(entryPoint.isDirectory()).thenReturn(true);
		when(entryPoint.isFile()).thenReturn(false);
		when(entryPoint.getPath()).thenReturn("entrypoint");
		when(entryPoint.listFiles()).thenReturn(new File[]{directory});

		List<File> deletableBaks = CleanFolders.cleanFolders(entryPoint);
		Assertions.assertEquals(1, deletableBaks.size());

		Mockito.verify(bakFile, Mockito.times(1)).delete();
		Mockito.verify(directory, Mockito.times(1)).delete();
		Mockito.verify(entryPoint, Mockito.never()).delete();
	}

	/*
    Tested structure:
    -topLevelDir
        |-directory
            |-whatever.bak
            |-whatever.doc
 */
	@Test
	public void oneDirectoryWithDocAndBak() {
		File bakFile = Mockito.mock(File.class);
		when(bakFile.isDirectory()).thenReturn(false);
		when(bakFile.isFile()).thenReturn(true);
		when(bakFile.getPath()).thenReturn("whatever.bak");

		File docFile = Mockito.mock(File.class);
		when(docFile.isDirectory()).thenReturn(false);
		when(docFile.isFile()).thenReturn(true);
		when(docFile.getPath()).thenReturn("whatever.doc");

		File directory = Mockito.mock(File.class);
		when(directory.isDirectory()).thenReturn(true);
		when(directory.isFile()).thenReturn(false);
		when(directory.getPath()).thenReturn("directory");
		when(directory.listFiles()).thenReturn(new File[]{bakFile, docFile});

		File entryPoint = Mockito.mock(File.class);
		when(entryPoint.isDirectory()).thenReturn(true);
		when(entryPoint.isFile()).thenReturn(false);
		when(entryPoint.getPath()).thenReturn("entrypoint");
		when(entryPoint.listFiles()).thenReturn(new File[]{directory});

		List<File> deletableBaks = CleanFolders.cleanFolders(entryPoint);
		Assertions.assertEquals(0, deletableBaks.size());

		Mockito.verify(bakFile, Mockito.never()).delete();
		Mockito.verify(docFile, Mockito.never()).delete();
		Mockito.verify(directory, Mockito.times(1)).delete();
		Mockito.verify(entryPoint, Mockito.never()).delete();
	}

	@Test
	public void allPossibleVariations() {
		//door
		door = Mockito.mock(File.class);
		when(door.getPath()).thenReturn("c:" + separatorInPath + "doOr");
		when(door.isDirectory()).thenReturn(true);
		when(door.isFile()).thenReturn(false);

		floor = Mockito.mock(File.class);
		when(floor.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "flooR");
		when(floor.isDirectory()).thenReturn(true);
		when(floor.isFile()).thenReturn(false);

		ceiling = Mockito.mock(File.class);
		when(ceiling.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "ceiLing");
		when(ceiling.isDirectory()).thenReturn(true);
		when(ceiling.isFile()).thenReturn(false);

		when(door.listFiles()).thenReturn(new File[]{floor, ceiling});

		//doOr/flooR
		abc_doc = Mockito.mock(File.class);
		when(abc_doc.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "flooR" + separatorInPath + "aBc.doc");
		when(abc_doc.isDirectory()).thenReturn(false);
		when(abc_doc.isFile()).thenReturn(true);

		abc_bak = Mockito.mock(File.class);
		when(abc_bak.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "flooR" + separatorInPath + "aBc.bak");
		when(abc_bak.isDirectory()).thenReturn(false);
		when(abc_bak.isFile()).thenReturn(true);

		cba_bak = Mockito.mock(File.class);
		when(cba_bak.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "flooR" + separatorInPath + "Cba.bak");
		when(cba_bak.isDirectory()).thenReturn(false);
		when(cba_bak.isFile()).thenReturn(true);

		sss_doc = Mockito.mock(File.class);
		when(sss_doc.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "flooR" + separatorInPath + "ssS.doc");
		when(sss_doc.isDirectory()).thenReturn(false);
		when(sss_doc.isFile()).thenReturn(true);

		sss_bak = Mockito.mock(File.class);
		when(sss_bak.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "flooR" + separatorInPath + "Sss.bak");
		when(sss_bak.isDirectory()).thenReturn(false);
		when(sss_bak.isFile()).thenReturn(true);

		teapot_xml = Mockito.mock(File.class);
		when(teapot_xml.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "flooR" + separatorInPath + "teapot.xml");
		when(teapot_xml.isDirectory()).thenReturn(false);
		when(teapot_xml.isFile()).thenReturn(true);

		question = Mockito.mock(File.class);
		when(question.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "flooR" + separatorInPath + "questioN");
		when(question.isDirectory()).thenReturn(true);
		when(question.isFile()).thenReturn(false);

		answer = Mockito.mock(File.class);
		when(answer.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "flooR" + separatorInPath + "Answer");
		when(answer.isDirectory()).thenReturn(true);
		when(answer.isFile()).thenReturn(false);

		when(floor.listFiles()).thenReturn(new File[]{abc_doc, abc_bak, cba_bak, sss_doc, sss_bak, teapot_xml, question, answer});

		//doOr/flooR/questioN
		when(question.listFiles()).thenReturn(new File[]{});

		//doOr/flooR/Answer
		quest = Mockito.mock(File.class);
		when(quest.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "flooR" + separatorInPath + "Answer" + separatorInPath + "Quest");
		when(quest.isDirectory()).thenReturn(true);
		when(quest.isFile()).thenReturn(false);
		when(answer.listFiles()).thenReturn(new File[]{quest});

		//doOr/ceiLing
		carpet = Mockito.mock(File.class);
		when(carpet.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "ceiLing" + separatorInPath + "carpeT");
		when(carpet.isDirectory()).thenReturn(true);
		when(carpet.isFile()).thenReturn(false);

		painting = Mockito.mock(File.class);
		when(painting.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "ceiLing" + separatorInPath + "paintinG");
		when(painting.isDirectory()).thenReturn(true);
		when(painting.isFile()).thenReturn(false);

		when(ceiling.listFiles()).thenReturn(new File[]{carpet, painting});

		//doOr/ceiLing/carpeT
		xyz_bak = Mockito.mock(File.class);
		when(xyz_bak.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "ceiLing" + separatorInPath + "carpeT" + separatorInPath + "xyz.bak");
		when(xyz_bak.isDirectory()).thenReturn(false);
		when(xyz_bak.isFile()).thenReturn(true);

		when(carpet.listFiles()).thenReturn(new File[]{xyz_bak});

		//doOr/ceiLing/paintinG
		aaa_xml = Mockito.mock(File.class);
		when(aaa_xml.getPath()).thenReturn("c:" + separatorInPath + "doOr" + separatorInPath + "ceiLing" + separatorInPath + "paintinG" + separatorInPath + "aaa.xml");
		when(aaa_xml.isDirectory()).thenReturn(false);
		when(aaa_xml.isFile()).thenReturn(true);

		when(painting.listFiles()).thenReturn(new File[]{aaa_xml});

		List<File> deletableBaks = CleanFolders.cleanFolders(door);
		Assertions.assertEquals(2, deletableBaks.size());

		Mockito.verify(door, Mockito.never()).delete();
		Mockito.verify(floor, Mockito.times(1)).delete();
		Mockito.verify(ceiling, Mockito.times(1)).delete();
		Mockito.verify(abc_doc, Mockito.never()).delete();
		Mockito.verify(abc_bak, Mockito.never()).delete();
		Mockito.verify(cba_bak, Mockito.times(1)).delete();
		Mockito.verify(sss_doc, Mockito.never()).delete();
		Mockito.verify(sss_bak, Mockito.never()).delete();
		Mockito.verify(teapot_xml, Mockito.never()).delete();
		Mockito.verify(question, Mockito.times(1)).delete();
		Mockito.verify(answer, Mockito.times(1)).delete();
		Mockito.verify(quest, Mockito.times(1)).delete();
		Mockito.verify(carpet, Mockito.times(1)).delete();
		Mockito.verify(painting, Mockito.times(1)).delete();
		Mockito.verify(xyz_bak, Mockito.times(1)).delete();
		Mockito.verify(aaa_xml, Mockito.never()).delete();
	}
}