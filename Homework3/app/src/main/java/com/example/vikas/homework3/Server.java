package com.example.vikas.homework3;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Server extends AsyncTask<String, Void, String> {

    Activity context;
    ProgressDialog ed;
    public Server(Activity activity)
        {
           this.context= activity ;

        }

    @Override
        protected String doInBackground(String... params) {

            try {
                String sourceFileUri = params[0];
                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 1 * 444;
                File sourceFile = new File(sourceFileUri);

                if (sourceFile.isFile()) {

                    try {
                        String upLoadServerUri = "http://192.168.52.81/fileUpload.php";

                        FileInputStream fileInputStream = new FileInputStream(
                                sourceFile);
                        URL url = new URL(upLoadServerUri);
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true);
                        conn.setDoOutput(true);
                        conn.setUseCaches(false);
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Connection", "Keep-Alive");
                        conn.setRequestProperty("ENCTYPE",
                                "multipart/form-data");
                        conn.setRequestProperty("Content-Type",
                                "multipart/form-data;boundary=" + boundary);
                        conn.setRequestProperty("upload", sourceFileUri);

                        dos = new DataOutputStream(conn.getOutputStream());

                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"upload\";filename=\""
                                + sourceFileUri + "\"" + lineEnd);

                        dos.writeBytes(lineEnd);

                        bytesAvailable = fileInputStream.available();

                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        buffer = new byte[bufferSize];
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                        float filesize = bytesAvailable;
                        float val = (float) 0.0;

                        while (bytesRead > 0) {
                            val = val + bytesRead;
                            dos.write(buffer, 0, bufferSize);
                            bytesAvailable = fileInputStream.available();
                            bufferSize = Math
                                    .min(bytesAvailable, maxBufferSize);
                            Thread.sleep(750);
                            bytesRead = fileInputStream.read(buffer, 0,
                                    bufferSize);
                            ed.setProgress((int)((val/filesize)*100.0));
                        }
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens
                                + lineEnd);

                        int serverResponseCode = conn.getResponseCode();
                        String serverResponseMessage = conn
                                .getResponseMessage();

                        if (serverResponseCode == 200) {
                            ed.setProgress(50);
                            return "Executed";
                        }
                        fileInputStream.close();
                        dos.flush();
                        dos.close();

                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            } catch (Exception ex) {

                ex.printStackTrace();
            }

            return "Not Executed";
        }

    @Override
        protected void onPostExecute(String result)
        {

            ed.dismiss();
            Log.i("LOG",result);

            if(result == "Executed")
            {
                Toast.makeText(context, "server found", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Server not found", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPreExecute() {

            ed = new ProgressDialog(context);
            ed.setMax(100);
            ed.setMessage("Uploading File");
            ed.setProgress(0);
            ed.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            ed.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }