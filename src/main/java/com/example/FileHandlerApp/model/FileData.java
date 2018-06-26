/**
 * 
 */
package com.example.FileHandlerApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Babita
 *
 */
@Entity
@Table(name = "FILEDATA")
public class FileData {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "FILEDATA_ID")
	private int id;
	private long size;
	@Column (name = "file_name")
	private String fileName;
	@Column (name = "is_empty")
	private boolean isEmpty;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the size
	 */

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the isEmpty
	 */
	public boolean isEmpty() {
		return isEmpty;
	}

	/**
	 * @param isEmpty
	 *            the isEmpty to set
	 */
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

}
