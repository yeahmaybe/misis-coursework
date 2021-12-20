package com.misis.coursework.service.upload;

import java.io.File;

public abstract class CsvImporterImpl implements CsvImporter {

    public abstract void importCsv(File file);

}
