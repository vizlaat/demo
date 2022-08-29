package com.example;

import java.io.*;
import java.util.*;

/**
 * Egy könyvtárstruktúrában dokumentumokat szerkesztenek.
 *
 * Minden dokumentumszerkesztéskor .bak kiterjesztésű fájlok biztonsági mentés jönnek létre
 * (doc1.doc-hoz doc1.bak), azonban gyakori, hogy a szerkesztő bezárása után ottmaradnak.
 *
 * Sok esetben a dokumentumokat már törlik is, és üres könyvtárra egyébként nem lenne szükség,
 * de a .bak fájlok miatt a könyvtárak megmaradnak.
 *
 * A feladat egy olyan program készítése, ami a paraméterként kapott könyvtárból kiindulva
 * kitisztítja a dokumentum nélkül ottmaradt .bak fájlokat, és az üres könyvtárakat is törli.
 *
 * A struktúra egy nagyon nagy (több milliós) virtuális fájlrendszer, és egy törlés viszonylag lassú művelet,
 * ezért fontos több szálon végezni a törléseket - a fájlrendszer elosztott,
 * ezért jól fogja bírni a párhuzamos terhelést.
 *
 * Megoldásként Java nyelvű forráskódot várunk (tetszőleges Java verzió használható).
 */
public class CleanFolders {
	private static final List<File> deletableBaks = new ArrayList<>();
	private static final List<File> folders = new ArrayList<>();

	/**
	 * Detects directories under the given directory and collects all .bak files without corresponding .doc files next to them.
	 * The method uses checkFolder() for detection and loneBakDelete() for deletion.
	 * @param startFolder	File of the starting directory.
	 */
	public static void bakDeletion(File startFolder) {
		checkFolder(startFolder);
		loneBakDelete();
	}

	/**
	 * Returns the collected, deletable .bak files.
	 * Created for opening a safe backdoor for testing purposes.
	 * @return	List<File> of .bak files.
	 */
	public static List<File> getDeletableBaks() {
		return deletableBaks;
	}

	/**
	 * Returns the collected directories.
	 * Created for opening a safe backdoor for testing purposes.
	 * @return	List<File> of directories.
	 */
	public static List<File> getFolders() {
		return folders;
	}

	/**
	 * Helper method for directory stream filtering and collecting.
	 * Also, launching the next recursive method call of checkFolder().
	 * @param file	File of processed folder.
	 */
	private static void folderHelper(File file) {
		folders.add(file);
		checkFolder(file);
	}

	/**
	 * Helper method for file filtering.
	 * @param file	File of processed file.
	 * @return		boolean value marks whether the checked file can be deleted.
	 */
	private static boolean isDeletableBak(File file) {
		String path = file.getPath();
		return file.isFile() &&
				file.getName().endsWith(".bak") &&
				!(new File(path.substring(0, path.length() - 4) + ".doc")).exists();
	}

	/**
	 * Processes directories and files in a directory.
	 * Recursive method for directories.
	 * Puts all directories and only deletable files into correspondent Lists.
	 * Files with .bak extension and without correspondent .doc file, can be deleted.
	 * @param folder	File of processed directory.
	 */
	private static void checkFolder(File folder) {
		try {
			File[] files = folder.listFiles();
			if ((files != null) && (files.length > 0)) {
				Arrays.stream(files)
						.filter(File::isDirectory)
						.forEach(CleanFolders::folderHelper);
				Arrays.stream(files)
						.filter(CleanFolders::isDeletableBak)
						.forEach(CleanFolders.deletableBaks::add);
			}
		}
		catch (Exception e) {
			//
		}
	}

	/**
	 * Deletes .bak files without correspondent .doc file next to them, then deletes all empty directories.
	 */
	private static void loneBakDelete() {
		deletableBaks.sort(Comparator.reverseOrder());
		try {
			for (File deletableBak : deletableBaks) {
				if (!deletableBak.delete()) {
					System.out.println("Unsuccessful deletion: " + deletableBak.getPath());
				}
			}
		}
		catch (Exception e) {
			System.out.println("File deletion was terminated due to exception.");
		}
		folders.sort(Comparator.reverseOrder());
		try {
			for (File folder : folders) {
				folder.delete();
			}
		}
		catch (Exception e) {
			System.out.println("Directory deletion was terminated due to exception.");
		}
	}
}
