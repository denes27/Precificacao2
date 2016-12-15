package com.example.denesleal.precificacao;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import static com.example.denesleal.precificacao.Ferramentas.emailUsuario;
import static com.example.denesleal.precificacao.Ferramentas.nomeUsuario;

// Classe principal
public class FazerOrcamento extends Activity {
    ArrayList<String> precos = new ArrayList<>();
    private final ArrayList<String> selecionados = new ArrayList<String>();

    ArrayList<String> PRODUTOS = new ArrayList<>();
    ArrayList<String> PRECOS = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MeuBD meuBD = new MeuBD(this,MeuBD.DB_NAME,null,MeuBD.DB_VERSION);
        ProdutosDAO produtosDAO = new ProdutosDAO(meuBD);
        ArrayList<Produtos> prod = (ArrayList<Produtos>) produtosDAO.read(null);


        //ADICIONAR EM PRODUTOS OS PRODUTOS CONTIDOS NO BD PARA FAZER O ORÇAMENTO

        if (prod == null){
            Toast.makeText(this, "Deu RUIM Forte", Toast.LENGTH_SHORT).show();
        }
        else if (prod.size() == 0){
            Toast.makeText(this, "Nenhum produto na Lista", Toast.LENGTH_SHORT).show();
        }
        else {
            int i = 0;
            for (Produtos produto : prod) {
                PRODUTOS.add(i, produto.getNome());
                PRECOS.add(i, "" + produto.getPreco());
                i++;
            }
        }


        // Define o arquivo /layout/main.xml como layout principal da aplicação
        setContentView(R.layout.activity_fazer_orcamento);

        // ListView
        ListView lsvEstados = (ListView) findViewById(R.id.LstCheckbox);

        // Adapter para implementar o layout customizado de cada item
        ArrayAdapter<String> lsvEstadosAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Recuperando o Estado selecionado de acordo com a sua posição no ListView
                String produto = PRODUTOS.get(position);
                String preco = PRECOS.get(position);

                // Se o ConvertView for diferente de null o layout já foi "inflado"
                View v = convertView;

                if(v==null) {
                    // "Inflando" o layout do item caso o isso ainda não tenha sido feito
                    LayoutInflater inflater = getLayoutInflater();
                    v = (View) inflater.inflate(R.layout.reslstcheckbox, null);
                }

                // Recuperando o checkbox
                CheckBox chk = (CheckBox) v.findViewById(R.id.chkProduto);

                // Definindo um "valor" para o checkbox
                chk.setTag(produto);

                /** Definindo uma ação ao clicar no checkbox. Aqui poderiamos armazenar um valor chave
                 * que identifique o objeto selecionado para que o mesmo possa ser, por exemplo, excluído
                 * mais tarde.
                 */
                chk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox chk = (CheckBox) v;
                        String produto = (String) chk.getTag();
                        if(chk.isChecked()) {
                            //Toast.makeText(getApplicationContext(), "Checkbox de " + produto + " marcado!", Toast.LENGTH_SHORT).show();
                            if(!selecionados.contains(produto))
                                selecionados.add(produto);
                        } else {
                           // Toast.makeText(getApplicationContext(), "Checkbox de " + produto + " desmarcado!", Toast.LENGTH_SHORT).show();
                            if(selecionados.contains(produto))
                                selecionados.remove(produto);
                        }
                    }
                });

                if(selecionados.contains(produto)) {
                    chk.setChecked(true);
                } else {
                    chk.setChecked(false);
                }


                // Preenche o TextView do layout com o nome do Estado
                TextView txv = (TextView) v.findViewById(R.id.txvProduto);
                txv.setText(produto);
                TextView txtpreco = (TextView) v.findViewById(R.id.txvPreco);
                txtpreco.setText(preco);

                return v;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public int getCount() {
                return PRODUTOS.size();
            }
        };
        lsvEstados.setAdapter(lsvEstadosAdapter);
    }
    public void selecionarProdutos (View view) throws IOException {

        int n = selecionados.size();
        EditText nomeCliente = (EditText)findViewById(R.id.edtnomeCliente);
        EditText emailCliente = (EditText)findViewById(R.id.edtemailCliente);

        if(nomeCliente.length() != 0 && emailCliente.length() != 0 && selecionados.size() != 0) {
            String nomecl = nomeCliente.getText().toString();
            String emailcl = emailCliente.getText().toString();
            String username = nomeUsuario;
            String useremail = emailUsuario;
            String msg = "TESTE";
            String tst = selecionados.get(0);
            Toast.makeText(this, "Itens no orçamento " + selecionados.size(), Toast.LENGTH_SHORT).show();

            String urlStr = "http://www.codehost.com.br/emprice/server.php";

            //String urlStr = "http://www.codehost.com.br/emprice/server.php?u_name="+nomecl+"&u_email="+emailcl+
            //        "&c_name="+username+"&c_email="+useremail+"&orcamento="+msg;

        /// O ENVIO DOS DADOS TEM QUE IR AQUI. urlStr é o endereço do servidor e os parametros que devem ser enviados
            /// são: u_name, u_email, c_name, c_email e orcamento

            // Create data variable for sent values to server

            String data = URLEncoder.encode("u_name", "UTF-8")
                    + "=" + URLEncoder.encode(nomecl, "UTF-8");

            data += "&" + URLEncoder.encode("u_email", "UTF-8") + "="
                    + URLEncoder.encode(emailcl, "UTF-8");

            data += "&" + URLEncoder.encode("c_name", "UTF-8")
                    + "=" + URLEncoder.encode(username, "UTF-8");

            data += "&" + URLEncoder.encode("c_email", "UTF-8")
                    + "=" + URLEncoder.encode(useremail, "UTF-8");

            data += "&" + URLEncoder.encode("orcamento", "UTF-8")
                    + "=" + URLEncoder.encode(msg, "UTF-8");

            String text = "";
            BufferedReader reader=null;

            // Send data
            try
            {

                // Defined URL  where to send data
                URL url = new URL(urlStr);

                // Send POST data request

                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write( data );
                wr.flush();

                // Get the server response

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    // Append server response in string
                    sb.append(line + "\n");
                }


                text = sb.toString();
            }
            catch(Exception ex)
            {

            }
            finally
            {
                try
                {

                    reader.close();
                }

                catch(Exception ex) {}
            }














            //DESCOMENTAR ISSO QUANDO TIVER TD PRONTO PRO APP VOLTAR A TELA INICIAL UMA VEZ QUE ESTEJA TUDO PRONTO
            //Intent intent = new Intent(this, Ferramentas.class);
            //startActivity(intent);


        }else Toast.makeText(this, "Preencha os campos de nome e email do cliente, e verifique se algum produto foi selecionado", Toast.LENGTH_SHORT).show();
    }

}

