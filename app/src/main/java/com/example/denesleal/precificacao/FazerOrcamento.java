package com.example.denesleal.precificacao;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// Classe principal
public class FazerOrcamento extends Activity {
    ArrayList<String> precos = new ArrayList<>();
    private final ArrayList<String> selecionados = new ArrayList<String>();

    MeuBD meuBD = new MeuBD(this,MeuBD.DB_NAME,null,MeuBD.DB_VERSION);
    ProdutosDAO produtosDAO = new ProdutosDAO(meuBD);
    ArrayList<Produtos> produtos = (ArrayList<Produtos>) produtosDAO.read(null);

    ArrayList<String> PRODUTOS = new ArrayList<>();
    ArrayList<String> PRECOS = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ADICIONAR EM PRODUTOS OS PRODUTOS CONTIDOS NO BD PARA FAZER O ORÇAMENTO
        PRODUTOS.add(0,"teste1");
        PRODUTOS.add(1,"teste2");
        PRECOS.add(0,"R$500");
        PRECOS.add(1,"R$1000");
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
                            Toast.makeText(getApplicationContext(), "Checkbox de " + produto + " marcado!", Toast.LENGTH_SHORT).show();
                            if(!selecionados.contains(produto))
                                selecionados.add(produto);
                        } else {
                            Toast.makeText(getApplicationContext(), "Checkbox de " + produto + " desmarcado!", Toast.LENGTH_SHORT).show();
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
    public void selecionarProdutos (View view){
        int n = selecionados.size();
        String tst = selecionados.get(0);
        Toast.makeText(this, "O primeiro elemento é "+tst, Toast.LENGTH_SHORT).show();
    }
}

