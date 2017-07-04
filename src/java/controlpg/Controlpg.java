/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlpg;

import conexao.Jpa;
import control.CaracteristiquestaosuperiorJpaController;
import control.ParametroautoavaliacaoJpaController;
import control.ParametrodocenteestudanteJpaController;

import control.ProfessorJpaController;
import control.QuestaoautoavaliacaoJpaController;
import control.QuestaodocenteestudanteJpaController;
import control.QuestaodocentesuperiorJpaController;
import java.awt.Event;
import java.util.List;
import modelo.Caracteristiquestaosuperior;
import modelo.Parametroautoavaliacao;
import modelo.Parametrodocenteestudante;
import modelo.Professor;
import modelo.Questaoautoavaliacao;
import modelo.Questaodocenteestudante;
import modelo.Questaodocentesuperior;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author ali_sualei
 */
public class Controlpg extends GenericForwardComposer{

    private String pesqNome;
// declaracao das listas
    private List<Professor> listadocente;
    private List<Parametrodocenteestudante> listaparametro;
    private List<Questaodocentesuperior> listaparametrosuper;
    private List<Parametroautoavaliacao> listaParamAutoAval;
    private List<Questaoautoavaliacao>listaQuestAutoAval;
    private List<Parametrodocenteestudante>listaParametroestuda;
    private List<Questaodocenteestudante>listaQuestEstuda;
    private List<Caracteristiquestaosuperior>listacaracteristic;
    private List<Questaodocentesuperior>listaquestsuper;
    
    @Wire
    org.zkoss.zul.Textbox codigo;
    @Wire
    Div autoavaliacao;
//declaracao da windows
    @Wire
    private Window winparametro,winquestao,winparametroestudante,winquestaoestu,wincaracterist,winsuperior;

    
    // metodo init serve para listar
    @AfterCompose
    public void init(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        listadocente = new ProfessorJpaController(new Jpa().getEmf()).findProfessorEntities();
        listaparametro = new ParametrodocenteestudanteJpaController(new Jpa().getEmf()).findParametrodocenteestudanteEntities();
        listaparametrosuper = new QuestaodocentesuperiorJpaController(new Jpa().getEmf()).findQuestaodocentesuperiorEntities();
        listaParamAutoAval = new ParametroautoavaliacaoJpaController(new Jpa().getEmf()).findParametroautoavaliacaoEntities();
        listaQuestAutoAval=new QuestaoautoavaliacaoJpaController(new Jpa().getEmf()).findQuestaoautoavaliacaoEntities();
        listaParametroestuda=new ParametrodocenteestudanteJpaController(new Jpa().getEmf()).findParametrodocenteestudanteEntities();
        listaQuestEstuda=new QuestaodocenteestudanteJpaController(new Jpa().getEmf()).findQuestaodocenteestudanteEntities();
        listacaracteristic=new CaracteristiquestaosuperiorJpaController(new Jpa().getEmf()).findCaracteristiquestaosuperiorEntities();
        listaquestsuper=new QuestaodocentesuperiorJpaController(new Jpa().getEmf()).findQuestaodocentesuperiorEntities();
    }
    
    
     @Command
    public void winquestao(){
       winquestao.doModal();
       
    }
    
   
    @Command
    public void winparametro() {
        winparametro.doModal();
       
    }

    @Command
    public void winparametroestudante(){
       winparametroestudante.doModal();
       
    }
    @Command
    public void winquestaoestu(){
       winquestaoestu.doModal();
       
    }
    
     @Command
    public void wincaracterist(){
       wincaracterist.doModal();
       
    }
     @Command
    public void winsuperior(){
       winsuperior.doModal();
       
    }
    
     public List<Caracteristiquestaosuperior> getListacaracteristic() {   
        return listacaracteristic;
    }

    public void setListacaracteristic(List<Caracteristiquestaosuperior> listacaracteristic) {
        this.listacaracteristic = listacaracteristic;
    }

    public Window getWincaracterist() {
        return wincaracterist;
    }
    
    public List<Questaodocentesuperior> getListaquestsuper() {
        return listaquestsuper;
    }

    public void setListaquestsuper(List<Questaodocentesuperior> listaquestsuper) {
        this.listaquestsuper = listaquestsuper;
    }

    public Window getWinsuperior() {
        return winsuperior;
    }

    // Geters e seters
    public void setWinsuperior(Window winsuperior) {    
        this.winsuperior = winsuperior;
    }

    public void setWincaracterist(Window wincaracterist) {
        this.wincaracterist = wincaracterist;
    }

    public List<Questaodocenteestudante> getListaQuestEstuda() {
        return listaQuestEstuda;
    }

    public void setListaQuestEstuda(List<Questaodocenteestudante> listaQuestEstuda) {
        this.listaQuestEstuda = listaQuestEstuda;
    }

    public Window getWinquestaoestu() {
        return winquestaoestu;
    }
    public void setWinquestaoestu(Window winquestaoestu) {
        this.winquestaoestu = winquestaoestu;
    }

    public Window getWinparametroestudante() {
        return winparametroestudante;
    }

    public void setWinparametroestudante(Window winparametroestudante) {
        this.winparametroestudante = winparametroestudante;
    }

    public List<Parametrodocenteestudante> getListaParametroestuda() {
        return listaParametroestuda;
    }

    public void setListaParametroestuda(List<Parametrodocenteestudante> listaParametroestuda) {
        this.listaParametroestuda = listaParametroestuda;
    }
    

    public Window getWinquestao() {
        return winquestao;
    }

    public void setWinquestao(Window winquestao) {
        this.winquestao = winquestao;
    }

    public List<Questaoautoavaliacao> getListaQuestAutoAval() {
        return listaQuestAutoAval;
    }

    public void setListaQuestAutoAval(List<Questaoautoavaliacao> listaQuestAutoAval) {
        this.listaQuestAutoAval = listaQuestAutoAval;
    }

    
    
    public Window getWinparametro() {
        return winparametro;
    }

    public void setWinparametro(Window winparametro) {
        this.winparametro = winparametro;
    }
    
    
    public List<Parametroautoavaliacao> getListaParamAutoAval() {
        return listaParamAutoAval;
    }

    public void setListaParamAutoAval(List<Parametroautoavaliacao> listaParamAutoAval) {
        this.listaParamAutoAval = listaParamAutoAval;
    }

    public List<Parametrodocenteestudante> getListaparametro() {
        return listaparametro;
    }

    public void setListaparametro(List<Parametrodocenteestudante> listaparametro) {
        this.listaparametro = listaparametro;
    }

    public List<Questaodocentesuperior> getListaparametrosuper() {
        return listaparametrosuper;
    }

    public void setListaparametrosuper(List<Questaodocentesuperior> listaparametrosuper) {
        this.listaparametrosuper = listaparametrosuper;
    }

    public String getPesqNome() {
        return pesqNome;
    }

    public void setPesqNome(String pesqNome) {
        this.pesqNome = pesqNome;
    }

    public List<Professor> getListadocente() {
        return listadocente;
    }

    public void setListadocente(List<Professor> listadocente) {
        this.listadocente = listadocente;
    }

  public void onSubmit$autoavaliacao(Event e){
      
         String codi = codigo.getValue();
       if(Strings.isBlank(codi)){
          Clients.showNotification("Informe a quantidade do parametro!", "warning", null, null, 3000);
       }
           else{
                Parametroautoavaliacao pau = new Parametroautoavaliacao();
               pau.setQuantidade(codigo.getText());
                new ParametroautoavaliacaoJpaController(new Jpa().getEmf()).create(pau);//Clients.evalJavaScript("jq('#"+autoavaliacao.getUuid()+"')[0].submit();");
      }
          Messagebox.show("Wrong input ");
      }
    
   

}
