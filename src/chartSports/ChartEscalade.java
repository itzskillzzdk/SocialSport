package chartSports;

import controller.Controller;
import controller.data_access_object.DataDAO;
import modules.donnees.DonneeEscalade;
import modules.sports.EscaladeTypeEnum;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import views.panels.GraphiqueSports;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ChartEscalade extends JFrame {

    private static final long serialVersionUID = 1L;
    private Controller controller;
    public ChartEscalade(String title, Controller controller, EnumType type) {
        super(title);
        this.controller = controller;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(GraphiqueSports.graphFrameDim);
        JPanel chartPanel = new JPanel(new GridLayout(1, 3));

        switch(type) {
            case DUREE:
                CategoryDataset dataset = createDatasetDuree();
                JFreeChart chart = createChartDuree(dataset);
                ChartPanel chartPanelDuree = new ChartPanel(chart);
                chartPanel.add(chartPanelDuree);
                break;
            case HAUTEUR:
                XYSeriesCollection datasetHauteur = createDatasetHauteur();
                JFreeChart chartHauteur = createChartHauteur(datasetHauteur);
                ChartPanel chartPanelHauteur = new ChartPanel(chartHauteur);
                chartPanel.add(chartPanelHauteur);
                break;
            case TYPE:
                PieDataset datasetStyle = createDatasetStyle();
                JFreeChart chartStyle = createChartStyle(datasetStyle);
                ChartPanel chartPanelStyle = new ChartPanel(chartStyle);
                chartPanel.add(chartPanelStyle);
                break;
        }
        setContentPane(chartPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }



    private CategoryDataset createDatasetDuree() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Utiliser une TreeMap pour trier les données par date croissante
        TreeMap<Date, Float> dureeByDate = new TreeMap<>();
        List<DonneeEscalade> donnees = DataDAO.getInstance().getDataEscalade(controller.getCurrentUser());
        for (DonneeEscalade donnee : donnees) {
            // Ajouter la durée à la valeur existante pour ce jour
            Date date = donnee.getDate();
            Float duree = Float.parseFloat(donnee.getDuree());
            dureeByDate.merge(date, duree, Float::sum);
        }

        // Ajouter les données à l'ensemble de données
        for (Map.Entry<Date, Float> entry : dureeByDate.entrySet()) {
            dataset.addValue(entry.getValue(), "Durée", entry.getKey().toString());
        }

        return dataset;
    }

    private XYSeriesCollection createDatasetHauteur() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        // Créer une série pour la hauteur
        XYSeries series = new XYSeries("Hauteur");

        // Récupérer les données d'escalade de l'utilisateur
        List<DonneeEscalade> donnees = DataDAO.getInstance().getDataEscalade(controller.getCurrentUser());

        // Ajouter les données à la série
        for (DonneeEscalade donnee : donnees) {
            series.add(donnee.getDate().getTime(), donnee.getHauteur());
        }

        // Ajouter la série au jeu de données
        dataset.addSeries(series);

        return dataset;
    }


    private JFreeChart createChartHauteur(XYDataset dataset) {
        return ChartFactory.createXYLineChart(
                "Hauteur d'escalade par jour",
                "Date",
                "Hauteur (m)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }
    private JFreeChart createChartDuree(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Durée de escalade par jour",
                "Date",
                "Durée (min)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        return chart;
    }
    private PieDataset createDatasetStyle() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Récupérer les données de natation de l'utilisateur
        List<DonneeEscalade> donnees = DataDAO.getInstance().getDataEscalade(controller.getCurrentUser());

        // Compter le nombre d'occurrences de chaque style de natation
        Map<EscaladeTypeEnum, Integer> styleCounts = new HashMap<>();
        for (DonneeEscalade donnee : donnees) {
            EscaladeTypeEnum style = donnee.getType();
            styleCounts.put(style, styleCounts.getOrDefault(style, 0) + 1);
        }

        // Ajouter les données au jeu de données du camembert
        for (Map.Entry<EscaladeTypeEnum, Integer> entry : styleCounts.entrySet()) {
            dataset.setValue(entry.getKey().toString(), entry.getValue());
        }

        return dataset;
    }

    private JFreeChart createChartStyle(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Répartition des types d'escalade",
                dataset,
                true,
                true,
                false);

        return chart;
    }
    public enum EnumType {
        TYPE,
        DUREE,
        HAUTEUR;
    }
}
