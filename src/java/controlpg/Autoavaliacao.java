/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlpg;

import control.ParametroautoavaliacaoJpaController;
import java.util.List;
import modelo.Parametroautoavaliacao;
import modelo.Questaoautoavaliacao;
import org.zkoss.bind.annotation.AfterCompose;
import conexao.Jpa;
import control.QuestaoautoavaliacaoJpaController;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
/**
 *
 * @author ali_sualei
 */
public class Autoavaliacao extends SelectorComposer<Component>  {
    
    private List<Questaoautoavaliacao> listaquestaoautoavaliao;
    private List<Parametroautoavaliacao> listaParamAutoAval;
    
    @Wire
   private Parametroautoavaliacao parametroautoavaliacao =new Parametroautoavaliacao() ;
   private ParametroautoavaliacaoJpaController pjc;
   
   @Wire
   private Window cadpauto;
////Machauma/////////////////////////////////////////////////////////////
    @Wire
    Button up, guardar, add;
    @Wire
    Listbox lista_autores, lista_autores2,lista_par,comboquest,combocodi;
    @Wire
    Div div_lista_autores;
    @Wire
    Textbox autor_search;
    @Wire
    Textbox codigo,quantidade,observacao;
    @Wire
    Textbox descricao;
    ListModelList<Parametroautoavaliacao> lista2 = new ListModelList<Parametroautoavaliacao>();
    
   
   
   
    public Parametroautoavaliacao getParametroautoavaliacao() {
        return parametroautoavaliacao;
    }

    public void setParametroautoavaliacao(Parametroautoavaliacao parametroautoavaliacao) {
        this.parametroautoavaliacao = parametroautoavaliacao;
    }
  
    public void onAdd(){
        Messagebox.show("jgghhghg");
    }
 
    
    
//   
    
//    @NotifyChange({"parametroautoavaliacao"})
//    @Command
//    public void cadparametroautoavaliacao(){
//        parametroautoavaliacao=new Parametroautoavaliacao();
//    }
  
    
    @AfterCompose
    public void init(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view,this,false);
    listaParamAutoAval = new ParametroautoavaliacaoJpaController(new Jpa().getEmf()).findParametroautoavaliacaoEntities();
    listaquestaoautoavaliao= new QuestaoautoavaliacaoJpaController(new Jpa().getEmf()).findQuestaoautoavaliacaoEntities();
    }
    

    
    @Listen("onClick = #addparameto")
    public void adicionarParametro() {
        String codi = codigo.getValue();
       if(Strings.isBlank(codi)){
          Clients.showNotification("Informe o codigo do parametro!", "warning", null, null, 3000);
       }
       else {
           if(isCodigo(codi))
               Clients.showNotification("O codigo ja existe!", "warning", null, null, 3000);
           else{
                Parametroautoavaliacao pau = new Parametroautoavaliacao();
               
               pau.setCodigo(codigo.getText());
               pau.setDescricao(descricao.getText());
               pau.setIdquestaoautoavaliacao((Questaoautoavaliacao) comboquest.getSelectedItem().getValue());    
                    
               try {
                    new ParametroautoavaliacaoJpaController(new Jpa().getEmf()).create(pau);
                    Clients.showNotification("parameto adicionado com sucesso!", "info", null, null, 3000);
                    codigo.setValue("");
                    descricao.setValue("");
               } catch (Exception e) {
                   Clients.showNotification("erro ao guardar", "warning", null, null, 3000);
               }
                   
               

            }
        }
        
    }
    @Listen("onClick = #addquantidade")
    public void adicionarQuntidade() {
       String codi = combocodi.getSelectedItem().getValue();
       if(Strings.isBlank(codi)){
          Clients.showNotification("Informe o codigo do parametro!", "warning", null, null, 3000);
       }
       else {
           if(isCodigo(codi)){
               Parametroautoavaliacao pau = new Parametroautoavaliacao();
               pau.setQuantidade(quantidade.getText());
               
            new ParametroautoavaliacaoJpaController(new Jpa().getEmf()).create(pau);
            Clients.showNotification("Auto avaliacao enviada com sucesso!", "info", null, null, 3000);
            
           }
           
        }
        
    }
    
     public boolean isCodigo(String codi){
         listaParamAutoAval = new ParametroautoavaliacaoJpaController(new Jpa().getEmf()).findParametroautoavaliacaoEntities();
        for (Parametroautoavaliacao a : listaParamAutoAval) {
            if(a.getCodigo() != null){
                if(a.getCodigo().equalsIgnoreCase(codi))
                    return true;
            }
        }
        return false;
    }
     
      @Listen("onCreate = #combo_curso; onCreate = #comboquest")
    public void doCriarListas() {
        
        ListModelList<Questaoautoavaliacao> listaquest = new ListModelList<Questaoautoavaliacao>(new QuestaoautoavaliacaoJpaController(new Jpa().getEmf()).findQuestaoautoavaliacaoEntities());
        comboquest.setModel(listaquest);
        
    }
    
      @Listen(" onCreate = #combocodi")
    public void doListarcodigo() {
        
        
        ListModelList<Parametroautoavaliacao> listcodi =new ListModelList<Parametroautoavaliacao>(new ParametroautoavaliacaoJpaController(new Jpa().getEmf()).findParametroautoavaliacaoEntities());
        combocodi.setModel(listcodi);
    }

    public ParametroautoavaliacaoJpaController getPjc() {
        return pjc;
    }

    public void setPjc(ParametroautoavaliacaoJpaController pjc) {
        this.pjc = pjc;
    }

    public Window getCadpauto() {
        return cadpauto;
    }

    public void setCadpauto(Window cadpauto) {
        this.cadpauto = cadpauto;
    }

    public Listbox getLista_autores() {
        return lista_autores;
    }

    public void setLista_autores(Listbox lista_autores) {
        this.lista_autores = lista_autores;
    }

    public Listbox getLista_autores2() {
        return lista_autores2;
    }

    public void setLista_autores2(Listbox lista_autores2) {
        this.lista_autores2 = lista_autores2;
    }

    public Div getDiv_lista_autores() {
        return div_lista_autores;
    }

    public void setDiv_lista_autores(Div div_lista_autores) {
        this.div_lista_autores = div_lista_autores;
    }

    public Textbox getAutor_search() {
        return autor_search;
    }

    public void setAutor_search(Textbox autor_search) {
        this.autor_search = autor_search;
    }

    public Textbox getCodigo() {
        return codigo;
    }

    public void setCodigo(Textbox codigo) {
        this.codigo = codigo;
    }

    public Textbox getDescricao() {
        return descricao;
    }

    public void setDescricao(Textbox descricao) {
        this.descricao = descricao;
    }

    public ListModelList<Parametroautoavaliacao> getLista2() {
        return lista2;
    }

    public void setLista2(ListModelList<Parametroautoavaliacao> lista2) {
        this.lista2 = lista2;
    }

    
    public List<Parametroautoavaliacao> getListaParamAutoAval() {
        return listaParamAutoAval;
    }

    public void setListaParamAutoAval(List<Parametroautoavaliacao> listaParamAutoAval) {
        this.listaParamAutoAval = listaParamAutoAval;
    }
    
    
      public List<Questaoautoavaliacao> getListaquestaoautoavaliao() {
        return listaquestaoautoavaliao;
    }

    public void setListaquestaoautoavaliao(List<Questaoautoavaliacao> listaquestaoautoavaliao) {
        this.listaquestaoautoavaliao = listaquestaoautoavaliao;
    }
    
}
