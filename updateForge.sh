#!/bin/bash

SNAPSHOT_ROOT="./conf/"
NOW=$(date +"%m_%d_%Y_%H_%M_%S")

BACKUP_DIR="$SNAPSHOT_ROOT/backup-$NOW"

URL="http://mcpbot.bspk.rs/testcsv/"
METHODS="methods.csv"
PARAMS="params.csv"
FIELDS="fields.csv"

function checkStatus {
	local status=$?
	if [ $status -ne 0 ]; then
		echo $1 >&2
		if [$2 = true]; then
			exit
		fi
	fi


	return $status
}

function backupMappings {
	echo '> Backing up mappings.'

	mkdir -p $BACKUP_DIR
	checkStatus 'Error making backup directory' true
	
	mv $SNAPSHOT_ROOT/*.csv $BACKUP_DIR/
	checkStatus 'Error backing up files.' true
}

function downloadMappings {
	echo '> Downloading newest mappings.'
	curl --create-dirs --output $SNAPSHOT_ROOT/$METHODS "$URL$METHODS"
	checkStatus 'Error downloading mapping! $URL$METHODS' false

	curl --create-dirs --output $SNAPSHOT_ROOT/$FIELDS "$URL$FIELDS"
	checkStatus 'Error downloading mapping! "$URL$FIELDS"' false

	curl --create-dirs --output $SNAPSHOT_ROOT/$PARAMS "$URL$PARAMS"
	checkStatus 'Error downloading mapping! "$URL$PARAMS"' false
}

function setupWorkspace {
	echo '> Setup Decomp Workspace'
	chmod +x ./gradlew
	./gradlew setupDecompWorkspace	
}

backupMappings
downloadMappings
setupWorkspace

echo 'REMINDER: Import as gradle project to your IDE if not already done.'