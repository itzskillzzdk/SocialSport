package chartSports;

import controller.Controller;
import controller.data_access_object.DataDAO;
import modules.donnees.DonneeNatation;
import modules.sports.NatationStyleEnum;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import views.panels.GraphiqueSports;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ChartNatation extends JFrame {
    private static final long serialVersionUID = 1L;
    private Controller controller;
    public ChartNatation(String title, Controller controller, EnumType type) {
        super(title);
        this.controller = controller;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(GraphiqueSports.graphFrameDim);
        JPanel chartPanel = new JPanel(new GridLayout(1, 2));

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
            case STYLE:
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

    private CategoryDataset createDatasetDistance() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Utiliser une Map pour regrouper les données par jour
        TreeMap<Date, Float> distanceByDate = new TreeMap<>();
        List<DonneeNatation> donnees = DataDAO.getInstance().getDataNatation(controller.getCurrentUser());
        for (DonneeNatation donnee : donnees) {
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

    private PieDataset createDatasetStyle() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Récupérer les données de natation de l'utilisateur
        List<DonneeNatation> donnees = DataDAO.getInstance().getDataNatation(controller.getCurrentUser());

        // Compter le nombre d'occurrences de chaque style de natation
        Map<NatationStyleEnum, Integer> styleCounts = new HashMap<>();
        for (DonneeNatation donnee : donnees) {
            NatationStyleEnum style = donnee.getStyle();
            styleCounts.put(style, styleCounts.getOrDefault(style, 0) + 1);
        }

        // Ajouter les données au jeu de données du camembert
        for (Map.Entry<NatationStyleEnum, Integer> entry : styleCounts.entrySet()) {
            dataset.setValue(entry.getKey().toString(), entry.getValue());
        }

        return dataset;
    }

    private JFreeChart createChartStyle(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Répartition des styles de natation",
                dataset,
                true,
                true,
                false);

        return chart;
    }

    private CategoryDataset createDatasetDuree() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Utiliser une TreeMap pour trier les données par date croissante
        TreeMap<Date, Float> dureeByDate = new TreeMap<>();
        List<DonneeNatation> donnees = DataDAO.getInstance().getDataNatation(controller.getCurrentUser());
        for (DonneeNatation donnee : donnees) {
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
                "Durée de natation par jour",
                "Date",
                "Durée (min)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        return chart;
    }
    public enum EnumType {
        DISTANCE,
        DUREE,
        STYLE;
    }
}
