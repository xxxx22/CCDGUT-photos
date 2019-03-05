package Thread;

import java.io.File;
import java.util.List;

import DownloadPhotos.DownloadPicFromURL;

public class RunnableUser extends Thread{
	
	private List<String> majorNo;
	private String nianji;
	
	private long startUsername;
	private long endUsername;
	private String start;
	private String end;
	
	private String url;
	private String path;
	private DownloadPicFromURL down = new DownloadPicFromURL();;
	private boolean isDown;
	private File file;
	
	public RunnableUser(List<String> majorNo, String nianji) {
		this.majorNo = majorNo;
		this.nianji = nianji;
	}
	
	@Override
	public void run() {
		try {
			for(int i=0; i<majorNo.size(); ++i) {
				start = nianji+ majorNo.get(i) + "101";
				startUsername = Long.parseLong(start);
				url = "http://10.20.208.7:88/upload/stu/" +startUsername+ ".jpg";
				path = "photos/ " +startUsername+ ".jpg";
				isDown = down.downloadPicture(url, path);
				if(!isDown) {//该年级专业不存在
					System.out.println("专业不存在");
					continue;
				}
				end = nianji+ majorNo.get(i) + "999";
				endUsername = Long.parseLong(end);
				for(; startUsername<endUsername; ++startUsername) {
					path = "photos/ " +startUsername+ ".jpg";
					file = new File(path);
					if(file.exists()) {//未下载
						System.out.println("已经下载");
						continue;
					}
					url = "http://10.20.208.7:88/upload/stu/" +startUsername+ ".jpg";
					isDown = down.downloadPicture(url, path);
					if(isDown) {
						System.out.println(url+ "下载成功");
					}else {
						//System.out.println(url+ "下载失败 , 照片不存在");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("线程中断");
            return;
		}
		
		
	}
	
}
