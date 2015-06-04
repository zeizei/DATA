package html;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PlayerImageHtml {
	private String htmlUrl;
	private String playerName;

	public PlayerImageHtml(String htmlUrl, String playerName) {
		this.htmlUrl = htmlUrl;
		this.playerName = playerName;
		System.out.println(playerName);
	}

	public void getImageJPG() {
		Document doc = null;
		try {
			doc = Jsoup.connect(htmlUrl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (doc != null) {
			Elements contents = doc.getElementsByClass("person_image");
			if (contents != null && contents.size() >= 1) {
				contents = contents.get(0).getElementsByTag("img");
				if (contents != null && contents.size() == 1) {
					String part[] = contents.get(0).toString().split("\"");
					if (part != null && part.length == 7) {
						String imgUrl = part[1];
						System.out.println(imgUrl);
						try {
							URL url = new URL(imgUrl);// 打开网络输入流
							DataInputStream dis = new DataInputStream(url.openStream());
							String newImageName = "D:/SE3/image_jpg/" + playerName + ".jpg";// 建立一个新的文件
							FileOutputStream fos = new FileOutputStream(new File(newImageName));
							byte[] buffer = new byte[1024];
							int length;// 开始填充数据
							while ((length = dis.read(buffer)) > 0) {
								fos.write(buffer, 0, length);
							}
							dis.close();
							fos.close();
						} catch (MalformedURLException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public void getImagePNG() {
		Document doc = null;
		try {
			doc = Jsoup.connect(htmlUrl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (doc != null) {
			Elements contents = doc.getElementsByTag("img");
			if (contents != null) {
				for (int i = 0; i < contents.size(); i++) {
					Elements playerImages = contents.get(i).getElementsByAttributeValueContaining("src", "http://d2cwpp38twqe55.cloudfront.net/images/images-002/players");
					if (playerImages != null && playerImages.size() >= 1) {
						System.out.println(playerImages.get(0));
						String part[] = playerImages.get(0).toString().split("\"");
						System.out.println(part.length);
						if (part != null && part.length == 9) {
							String imgUrl = part[3];
							try {
								URL url = new URL(imgUrl);// 打开网络输入流
								DataInputStream dis = new DataInputStream(url.openStream());
								String newImageName = "D:/SE3/image_png/" + playerName + ".png";// 建立一个新的文件
								FileOutputStream fos = new FileOutputStream(new File(newImageName));
								byte[] buffer = new byte[1024];
								int length;// 开始填充数据
								while ((length = dis.read(buffer)) > 0) {
									fos.write(buffer, 0, length);
								}
								dis.close();
								fos.close();
							} catch (MalformedURLException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}
