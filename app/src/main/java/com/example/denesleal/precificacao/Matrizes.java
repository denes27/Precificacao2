package com.example.denesleal.precificacao;




import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Matrizes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrizes);
        setSpinner();
        setSpinner2();
    }




    //Declarando uma lista de Strings pros nomes das matérias primas e um int pra controle da posição na lista
    public ArrayList<String> lista = new ArrayList<String>();
    public int n = 0;
    public ArrayList<Float> listapreco = new ArrayList<Float>();
    public ArrayList<String> listaunidade = new ArrayList<String>();
    public ArrayList<String> listaunidadeusada = new ArrayList<String>();
    public ArrayList<String> opcunidades = new ArrayList<String>();
    public ArrayList<Float> listaqtdusada = new ArrayList<Float>();
    private static final int CODE_ACT=1;

//Array de tipos de unidade para usuário escolher (ex: kg de farinha, unidade de ovo)
    /*public void setOpcunidades(ArrayList<String> opcunidades) {
        this.opcunidades = opcunidades;
        opcunidades.add(0,"Unidades");
        opcunidades.add(1,"Kg");
        opcunidades.add(2,"g");
        opcunidades.add(3,"Litro(s)");
        opcunidades.add(4,"mL");
    }*/
    /*   //Adiciona dados na lista  TENTATIVA DE FAZER COM EXPANDABLE LIST N DEU CERTO
    public void preencheLista(View view){

        //Colocando o que o usuário digitou no editText em uma String mp
        EditText editmp = (EditText)findViewById(R.id.editmp);
        String mp = editmp.getText().toString();
        //Pegar valor do edt Preço
        EditText editmppreco = (EditText)findViewById(R.id.editmppreco);
        float mppreco = Float.valueOf(editmppreco.getText().toString());
        //adiciona a lista na posição n o que estiver escrito no editText
        Toast.makeText(this, "Valor do preço é "+mppreco, Toast.LENGTH_SHORT).show();
        lista.add(n, mp);

        //Fazendo aparecer na lista e incrementando n.
        ExpandableListView lstDados = (ExpandableListView)findViewById(R.id.explistDados);
        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                lista);
        lstDados.setAdapter(adaptador);
        n++;
    }*/


    public void preencheLista(View view){

        //Colocando o que o usuário digitou no editText em uma String mp
        EditText editmp = (EditText)findViewById(R.id.editmp);
        String mp = editmp.getText().toString();
        //Pegar valor do edt Preço
        EditText editmppreco = (EditText)findViewById(R.id.editmppreco);
        float mppreco = Float.valueOf(editmppreco.getText().toString());
        //mesma coisa pra quantidade de mp usada
        EditText editqtd = (EditText)findViewById(R.id.edtqtd);
        float edtqtd = Float.valueOf(editqtd.getText().toString());

        //Pegar valor das quantidades através dos Spinners
        Spinner spnunidade = (Spinner)findViewById(R.id.spnunidade);
        String unidade = spnunidade.getSelectedItem().toString();
        Spinner spnunidade2 = (Spinner)findViewById(R.id.spnunidade2);
        String unidade2 = spnunidade2.getSelectedItem().toString();


        //adiciona a lista na posição n o que estiver escrito no editText
        lista.add(n, mp+" R$"+mppreco+" por "+unidade+ " e "+edtqtd+" "+unidade2+" usados.");
        listapreco.add(n,mppreco);
        listaunidade.add(n,unidade);
        listaunidadeusada.add(n,unidade2);
        listaqtdusada.add(n,edtqtd);

        //Fazendo aparecer na lista e incrementando n.
        ListView lstDados = (ListView)findViewById(R.id.lstDados);
        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                lista);
        lstDados.setAdapter(adaptador);
        n++;
    }

    //Remove o último item da lista
    public void tirarLista(View view){
        //se n for 0 e for subtraído o programa da pau, portanto o if.
        if (n>=1){
            n--;
            lista.remove(n);
            listapreco.remove(n);
            listaqtdusada.remove(n);
            listaunidadeusada.remove(n);
            listaunidade.remove(n);
            ListView lstDados = (ListView)findViewById(R.id.lstDados);
            ArrayAdapter<String> adaptador;
            adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
            lstDados.setAdapter(adaptador);
        }else Toast.makeText(this, "Lista Vazia", Toast.LENGTH_SHORT).show();

    }
    public void setSpinner() {
        ArrayList<String> opcunidades = new ArrayList<String>();
        opcunidades.add(0,"Unidades");
        opcunidades.add(1,"Kg");
        opcunidades.add(2,"g");
        opcunidades.add(3,"Litro(s)");
        opcunidades.add(4,"mL");
        Spinner spnunidades = (Spinner) findViewById(R.id.spnunidade);
        ArrayAdapter<String> spnadapter;
        spnadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, opcunidades);
        spnadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnunidades.setAdapter(spnadapter);
    }
    public void setSpinner2() {
        ArrayList<String> opcunidades = new ArrayList<String>();
        opcunidades.add(0,"Unidades");
        opcunidades.add(1,"Kg");
        opcunidades.add(2,"g");
        opcunidades.add(3,"Litro(s)");
        opcunidades.add(4,"mL");
        Spinner spnunidades = (Spinner) findViewById(R.id.spnunidade2);
        ArrayAdapter<String> spnadapter;
        spnadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, opcunidades);
        spnadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnunidades.setAdapter(spnadapter);
    }
    public static float precoMP = 0;
    public void calculoMP(View view){
        precoMP = 0;
        int m = 0;
        if(m != listapreco.size()){
       // Toast.makeText(this, "Cheguei até antes do for", Toast.LENGTH_SHORT).show();
        for(m = 0;m < listapreco.size();m++){
           // Toast.makeText(this, "Cheguei até o for "+m, Toast.LENGTH_SHORT).show();
            //Se as unidades usadas e compradas forem iguais:
            if(listaunidade.get(m).equals(listaunidadeusada.get(m))){
                precoMP = precoMP + listapreco.get(m)*listaqtdusada.get(m);
                //se as unidades tivem uma relação de kg - g, ou L -ml:
            }else if((listaunidade.get(m).equals("Kg")  && listaunidadeusada.get(m).equals("g") ) ||
                    (listaunidade.get(m).equals("Litro(s)") && listaunidadeusada.get(m).equals("mL"))){
                precoMP = precoMP + listapreco.get(m)*(listaqtdusada.get(m)/1000);
                //Em qualquer outro caso deve haver inconsistência (ex: comprar 200g e usar 1 litro):
            }else {
                Toast.makeText(this, "Houve algum erro na conversão de unidades. Verifique a lista feita.", Toast.LENGTH_LONG).show();

            }
        }
            //Anuncia o valor calculado pelo método e chama a activity da proxima parte (CalculoDespesas)
        Toast.makeText(this, "O custo relativo a matéria prima por produto é: "+precoMP, Toast.LENGTH_SHORT).show();
            Bundle pacote = new Bundle();
            pacote.putFloat("preco", precoMP);

            Intent intent2 = new Intent(this, CalculoDespesas.class);
            intent2.putExtras(pacote);

        Intent intent = new Intent(this,CalculoDespesas.class);
        startActivity(intent);} else Toast.makeText(this, "Não há nada na lista.", Toast.LENGTH_SHORT).show();
    }

    //Envio de arquivos pra outra activity
    public void enviarDados(){

        // vou criar um bundle para passar o texto para a 2a
        // activity

        Bundle pacote = new Bundle();
        pacote.putFloat("preco", precoMP);

        Intent intent = new Intent(this, CalculoDespesas.class);
        intent.putExtras(pacote);

        // para cada Activity que inicio aguardando resultado,
        // devo passar um código para ela
        startActivityForResult(intent,CODE_ACT);
    }

    public void chamaDespesa(View view){

    }

}
