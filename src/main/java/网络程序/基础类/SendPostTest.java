package �������.������;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * ʹ��Post����
 */
public class SendPostTest {
    public static void main(String[] args) throws IOException {
        // ����URL��ַ
        String urlPath = "http://www.1lin.xyz";
        // �������������ύ������(��ֵ��)
        String param = "name" + URLEncoder.encode("abc","UTF-8");
        // ����URL
        URL url = new URL(urlPath);
        // �õ�����
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // �����Ӷ������ò���,ʹ��Post�������Ϊtrue
        conn.setDoOutput(true);
        // �����Ӷ������ò���,ʹ��Post�������Ϊtrue(��ѡ��Ĭ��Ϊtrue)
        conn.setDoInput(true);
        // ���ý�ֹ�����ʹ�û���
        conn.setUseCaches(false);
        // ��������ʽ
        conn.setRequestMethod("POST");
        // ��������ͷ��Ϣ,�����ַ���
        conn.setRequestProperty("Charset","UTF-8");
        // ��������ģʽ,������Ӧ�������ж�����
        conn.setRequestProperty("Connection","Keep-Alive");
        // �����ύ�����ݱ����ʽ
        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

        // ����(�ɲ�д,Ĭ�ϻ�����)
        conn.connect();
        // ��ȡ�����,�����ݷŵ�������(��װΪ���������),д�����������ľ���������������������
        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        // �������д�����(�����ݷŵ��ڴ��еĻ�������)
        dos.writeBytes(param);
        // ˢ�»�����(����д��)
        dos.flush();
        // �ر���
        dos.close();

        // ��ȡ���������ػ�����״̬����
        int resultCode = conn.getResponseCode();
        // ���״ֵ̬����200(200Ϊ�ɹ�״̬)
        if (resultCode == HttpURLConnection.HTTP_OK){
            // �ѻ�ȡ��������ת��Ϊ�ַ���,֮���ڰ�װ�ɴ����������ַ���
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            // �����ַ���
            String line = null;
            // ��������ڿ�(����������)
            while ((line=reader.readLine())!=null){
                // �������
                System.out.println(line);
            }
        }
    }
}
