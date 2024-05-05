package chartSports;

import controller.Controller;
import controller.data_access_object.DataDAO;
import modules.donnees.DonneeJogging;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import views.panels.GraphiqueSports;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ChartJogging extends JFrame {

    private static final long serialVersionUID = 1L;
    private Controller controller;
    public ChartJogging(String title, Controller controller, EnumType type) {
        super(title);
        this.controller = controller;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(GraphiqueSports.graphFrameDim);
        JPanel chartPanel = new JPanel(new GridLayout(1, 3));

        switch(type) {
            case DISTANCE:
                CategoryDataset datasetDistance = createDatasetDistance();
                JFreeChart chartDistance = createChartDistance(datasetDistance);
                ChartPanel chartPanelDistance = new ChartPanel(chartDistance);
                chartPanel.add(chartPanelDistance);
                break;
            case DUREE:
                CategoryDataset datasetDuree = createDatasetDuree();
                JFreeChart chartDuree = createChartDuree(datasetDuree);
                ChartPanel chartPanelDuree = new ChartPanel(chartDuree);
                chartPanel.add(chartPanelDuree);
                break;
            case VITESSE:
                XYSeriesCollection datasetVitesse = createDatasetVitesse();
                JFreeChart chartVitesse = createChartVitesse(datasetVitesse);
                ChartPanel chartPanelVitesse = new ChartPanel(chartVitesse);
                chartPanel.add(chartPanelVitesse);
                break;
        }
        setContentPane(chartPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private CategoryDataset createDatasetDistance() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Utiliser une Map pour regrouper les données par jour
        TreeMap<Date, Float> distanceByDate = new TreeMap<>();
        List<DonneeJogging> donnees = DataDAO.getInstance().getDataJogging(controller.getCurrentUser());
        for (DonneeJogging donnee : donnees) {
            // Ajouter la distance à la valeur existante pour ce jour
            Date date = donnee.getDate();
            Float distance = donnee.getDistance();
            distanceByDate.merge(date, distance, Float::sum);
        }

        // Ajouter les données à l'ensemble de données
        for (Map.Entry<Date, Float> entry : distanceByDate.entrySet()) {
            dataset.addValue(entry.getValue(), "Distance", entry.getKey().toString());
        }

        return dataset;
    }

    private JFreeChart createChartDistance(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Distance parcourue par jour",
                "Date",
                "Distance (m)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        return chart;
    }

    private CategoryDataset createDatasetDuree() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Utiliser une TreeMap pour trier les données par date croissante
        TreeMap<Date, Float> dureeByDate = new TreeMap<>();
        List<DonneeJogging> donnees = DataDAO.getInstance().getDataJogging(controller.getCurrentUser());
        for (DonneeJogging donnee : donnees) {
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


    private JFreeChart createChartDuree(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Durée de jogging par jour",
                "Date",
                "Durée (min)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        return chart;
    }

    private XYSeriesCollection createDatasetVitesse() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        // Créer une série pour la vitesse
        XYSeries series = new XYSeries("Vitesse");

        // Utiliser une TreeMap pour regrouper les données par jour
        TreeMap<Date, Float> vitesseByDate = new TreeMap<>();
        List<DonneeJogging> donnees = DataDAO.getInstance().getDataJogging(controller.getCurrentUser());
        for (DonneeJogging donnee : donnees) {
            // Calculer la vitesse (distance / durée) pour ce jour
            Date date = donnee.getDate();
            Float distance = donnee.getDistance();
            Float duree = Float.parseFloat(donnee.getDuree())*60;
            if (duree != 0) {
                Float vitesse = distance / duree;
                vitesseByDate.put(date, vitesse);
            }
        }

        // Ajouter les données à la série
        for (Map.Entry<Date, Float> entry : vitesseByDate.entrySet()) {
            series.add(entry.getKey().getTime(), entry.getValue());
        }

        // Ajouter la série au jeu de données
        dataset.addSeries(series);

        return dataset;
    }

    private JFreeChart createChartVitesse(XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Vitesse de jogging par jour",
                "Date",
                "Vitesse (m/s)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        return chart;
    }
    public enum EnumType {
        VITESSE,
        DUREE,
        DISTANCE;
    }
}
