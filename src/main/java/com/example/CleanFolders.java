package com.example;


import java.io.*;
import java.util.*;
import java.util.stream.*;

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
	public static List<File> cleanFolders(File startFolder) {
		List<File> collectedFoldersAndBaks = new ArrayList<>();
		checkFolder(startFolder, collectedFoldersAndBaks);
		List<File> loneBaks = collectedFoldersAndBaks.stream()
						.filter(File::isFile)
						.collect(Collectors.toList());
		deleteLoneBaksAndEmptyFolders(collectedFoldersAndBaks);
		return loneBaks;
	}

	private static boolean isDeletableBak(File file, File[] files) {
		if (file.getPath().endsWith(".bak")) {
			String path = file.getPath();
			return !Arrays.asList(files).contains(new File(path.substring(0, path.length() - 4) + ".doc"));
		}
		return false;
	}

	//Files with .bak extension and without correspondent .doc file, can be deleted.
	//Empty folders can be deleted.
	private static void checkFolder(File folder, List<File> foldersAndFiles) {
			File[] files = folder.listFiles();
			if ((files != null) && (files.length > 0)) {
				for (File file : files) {
					if (file.isDirectory() || CleanFolders.isDeletableBak(file, files)) {
						foldersAndFiles.add(file);
						if (file.isDirectory()) {
							checkFolder(file, foldersAndFiles);
						}
					}
				}
			}
	}

	private static void deleteLoneBaksAndEmptyFolders(List<File> collectedFoldersAndBaks) {
		collectedFoldersAndBaks.sort(Comparator.reverseOrder());
		collectedFoldersAndBaks
				.forEach(file -> {
					try {
						if (file.isFile()) {
							if (!file.delete()) {
								System.out.println("File deletion was unsuccessful: " + file.getPath());
							}
						}
						else {
							file.delete();
						}
					}
					catch (Exception e) {
						if (file.isFile()) {
							System.out.println("File deletion was terminated: " + file.getPath());
						}
						else {
							System.out.println("Unable to access directory: " + file.getPath());
						}
						e.getStackTrace();
					}
				});
	}
}
