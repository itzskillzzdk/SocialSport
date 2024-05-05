package chartSports;

import controller.Controller;
import controller.data_access_object.DataDAO;
import modules.donnees.DonneeMusculation;
import modules.sports.MusculationExerciceEnum;
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

public class ChartMusculation extends JFrame {

    private static final long serialVersionUID = 1L;
    private Controller controller;
    public ChartMusculation(String title, Controller controller, EnumType type) {
        super(title);
        this.controller = controller;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(GraphiqueSports.graphFrameDim);
        JPanel chartPanel = new JPanel(new GridLayout(1, 3));
        switch(type) {
            case REPETITION:
                CategoryDataset dataset = createDatasetRepetition();
                JFreeChart chart = createChartRepetition(dataset);
                ChartPanel chartPanelDuree = new ChartPanel(chart);
                chartPanel.add(chartPanelDuree);
                break;
            case POIDS:
                XYSeriesCollection datasetHauteur = createDatasetPoidsLeve();
                JFreeChart chartHauteur = createChartPoidsLeve(datasetHauteur);
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



    private CategoryDataset createDatasetRepetition() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Utiliser une TreeMap pour trier les données par date croissante
        TreeMap<Date, Integer> repetitionByDate = new TreeMap<>();
        List<DonneeMusculation> donnees = DataDAO.getInstance().getDataMusculation(controller.getCurrentUser());
        for (DonneeMusculation donnee : donnees) {
            // Ajouter la durée à la valeur existante pour ce jour
            Date date = donnee.getDate();
            int repetition = (donnee.getRepetition());
            repetitionByDate.merge(date, repetition, Integer::sum);
        }

        // Ajouter les données à l'ensemble de données
        for (Map.Entry<Date, Integer> entry : repetitionByDate.entrySet()) {
            dataset.addValue(entry.getValue(), "Durée", entry.getKey().toString());
        }

        return dataset;
    }

    private XYSeriesCollection createDatasetPoidsLeve() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        // Créer une série pour la hauteur
        XYSeries series = new XYSeries("Poids levé");

        // Récupérer les données de musculation de l'utilisateur
        List<DonneeMusculation> donnees = DataDAO.getInstance().getDataMusculation(controller.getCurrentUser());

        // Ajouter les données à la série
        for (DonneeMusculation donnee : donnees) {
            series.add(donnee.getDate().getTime(), donnee.getPoidsLeve());
        }

        // Ajouter la série au jeu de données
        dataset.addSeries(series);

        return dataset;
    }


    private JFreeChart createChartPoidsLeve(XYDataset dataset) {
        return ChartFactory.createXYLineChart(
                "Poids levé par jour",
                "Date",
                "Poids Levé (Kg)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }
    private JFreeChart createChartRepetition(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Nombre de répetition par jour",
                "Date",
                "Nombre de répetitions ",
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
        List<DonneeMusculation> donnees = DataDAO.getInstance().getDataMusculation(controller.getCurrentUser());

        // Compter le nombre d'occurrences de chaque style de natation
        Map<MusculationExerciceEnum, Integer> styleCounts = new HashMap<>();
        for (DonneeMusculation donnee : donnees) {
            MusculationExerciceEnum style = donnee.getExercice();
            styleCounts.put(style, styleCounts.getOrDefault(style, 0) + 1);
        }

        // Ajouter les données au jeu de données du camembert
        for (Map.Entry<MusculationExerciceEnum, Integer> entry : styleCounts.entrySet()) {
            dataset.setValue(entry.getKey().toString(), entry.getValue());
        }

        return dataset;
    }

    private JFreeChart createChartStyle(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Répartition des exercices de musculation",
                dataset,
                true,
                true,
                false);

        return chart;
    }
    public enum EnumType {
        REPETITION,
        POIDS,
        TYPE;
    }
}
