package io.vteial.watchyoursales.service;

public interface BackupAndRestoreService {

	boolean isInProgress();

	void reset();

	// String backup();
	//
	// void restore(String backupId);
}
