package com.njkol.references.soft;

public class ImageLoader {

	private static SoftReferenceCache<String, Image> cache = new SoftReferenceCache<String, Image>();

	public static Image getImage(String key) {

		Image result = null;

		Image valueCache = (Image) cache.get(key);

		if (valueCache == null) {
			System.out.println("entry: " + key + " result: cache miss");
			Image newValue = new Image("Cats");
			cache.put(key, newValue);
			result = newValue;
		} else {
			System.out.println("entry: " + key + " result: cache hit");
		}
		return result;
	}
}

class Image {

	private String imageName;

	public Image(String imageName) {
		super();
		this.imageName = imageName;
	}

	public String getImageName() {
		return imageName;
	}
}