package utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import database.GestorDB;
import juego.Boosts;
import objetos.Usuario;




public class utils {

	
	
    public static void alert(String infoMsg, String titulo, String iconoName) {
    	ImageIcon icono = getIcono(iconoName);
		JOptionPane.showMessageDialog(null, infoMsg, titulo, JOptionPane.INFORMATION_MESSAGE,icono);
	}
    
    public static void alert(String infoMsg, String titulo) {
		JOptionPane.showMessageDialog(null, infoMsg, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
    
    
    public static ImageIcon getIcono(String string) {
    	ImageIcon iconoImg = new ImageIcon(Boosts.class.getResource("/srcmedia/"+string+".png"));
        Image imagenEscalada = iconoImg.getImage().getScaledInstance((int) (Escalador.escalar(35)), (int) (Escalador.escalar(35)), Image.SCALE_SMOOTH);
		ImageIcon icono = new ImageIcon(imagenEscalada);
		return icono;
	}
    
    
    public static Image adjustImageOpacity(Image image, double opacity) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) opacity));
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }
    
	
	
	public static BufferedImage getRoundImg(String imageSource, int size) {
        try {
            // Convierte la imagen en un BufferedImage
            BufferedImage originalImage;

            if (imageSource.toLowerCase().startsWith("http://") || imageSource.toLowerCase().startsWith("https://")) {
                // Trata la cadena como una URL
                URL imageURL = new URL(imageSource);
                InputStream imageStream = imageURL.openStream();
                originalImage = ImageIO.read(imageStream);
            } else {
                // Asume que es una ruta de archivo local
                InputStream imageStream = utils.class.getResourceAsStream(imageSource);
                originalImage = ImageIO.read(imageStream);
            }

            // Redimensiona la imagen a un tamaño más pequeño
            int width = size;
            int height = size;
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Crea un BufferedImage con el tamaño especificado y formato ARGB
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = resizedImage.createGraphics();

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            // Dibuja la imagen escalada en el BufferedImage redimensionado
            Shape circle = new Ellipse2D.Float(0, 0, width, height);
            g2d.setClip(circle);
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();

            return resizedImage;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void uploadFile(File selectedFile) {
    	HttpClient client = HttpClient.newHttpClient();
        try {
        	//puede que en el futuro lo cambiemos a un string aleatorio en vez del nombre del usuario
            String fileName = Session.getCurrentUser().getUsername() + selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
            
            
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Configuracion.UPLOAD_URL + fileName))
                .PUT(HttpRequest.BodyPublishers.ofFile(selectedFile.toPath()))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
			if (response.statusCode() == 201) {
				Usuario curUser = Session.getCurrentUser();
				curUser.setImg_route(Configuracion.UPLOAD_URL +Configuracion.UPLOAD_DIR+ fileName);
				GestorDB.modificarImagenUsuario(curUser.getUsername(),Configuracion.UPLOAD_URL+ Configuracion.UPLOAD_DIR + fileName);
			} else {
				System.out.println("Error al subir el archivo");
			}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }



	
	
}
