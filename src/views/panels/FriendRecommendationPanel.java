package views.panels;

import controller.Controller;
import modules.DemandeAmis;
import modules.User;
import views.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class FriendRecommendationPanel extends JPanel {
    private Controller controller;
    public FriendRecommendationPanel(Controller controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        setPreferredSize(MainPanel.mainPanelDim);
        setMinimumSize(MainPanel.mainPanelDim);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleJL = new JLabel("Recommendations d'ami·e·s: ");
        titleJL.setFont(new Font("Arial", Font.BOLD,24));
        titleJL.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(30));
        add(titleJL);
        controller.creeRecommendationAmis();
        for (DemandeAmis d : controller.getCurrentUser().getDemandes()) {
            if(d.getStatut() != DemandeAmis.StatutDemande.ACCEPTEE) {
                add(Box.createVerticalStrut(20));
                add(createRecommendationCard(d.getDemandeur()));
            }
        }
    }
    private JPanel createRecommendationCard(User friend) {
        int cardWidth = 800;
        int cardHeight = 150;
        Dimension cardDimension = new Dimension(cardWidth,cardHeight);

        JPanel card = new JPanel();
        card.setPreferredSize(cardDimension);
        card.setMinimumSize(cardDimension);
        card.setMaximumSize(cardDimension);
        card.setLayout(new BoxLayout(card, BoxLayout.X_AXIS));

        JPanel leftCard = new JPanel();
        leftCard.setPreferredSize(new Dimension(cardHeight, cardHeight));
        leftCard.setMinimumSize(new Dimension(cardHeight, cardHeight));
        leftCard.setLayout(new BoxLayout(leftCard, BoxLayout.Y_AXIS));
        JLabel friendPicture = new JLabel(Utils.getResizeImage("src/photo/avatar2.jpg", 128, 128));
        friendPicture.setAlignmentX(CENTER_ALIGNMENT);
        friendPicture.setAlignmentY(CENTER_ALIGNMENT);

        leftCard.add(friendPicture);

        JPanel rightCard = new JPanel();
        rightCard.setPreferredSize(new Dimension(cardWidth - cardHeight, cardHeight));
        rightCard.setMinimumSize(new Dimension(cardWidth - cardHeight, cardHeight));
        rightCard.setLayout(new BoxLayout(rightCard, BoxLayout.Y_AXIS));

        String friendName = friend.getNom();
        String friendSports = friend.sportListToString();

        JLabel friendNameJL = new JLabel(friendName);
        JLabel friendSportsJL = new JLabel(friendSports);
        friendNameJL.setFont(new Font("Arial", Font.BOLD, 18));
        friendSportsJL.setFont(new Font("Arial", Font.ITALIC, 14));

        JButton delBtn = new JButton("Supprimer la demande");
        delBtn.addActionListener(e -> {
            controller.supprimerDemandeDe(friend);
            remove(card);
            repaint();
        });

        JButton addBtn = new JButton("Ajouter en ami");
        addBtn.addActionListener(e -> {
            controller.ajouterEnAmi(friend);
            remove(card);
            repaint();
        });

        rightCard.setOpaque(false);

        rightCard.add(friendNameJL);
        rightCard.add(Box.createVerticalStrut(5));
        rightCard.add(friendSportsJL);
        rightCard.add(Box.createVerticalStrut(20));
        rightCard.add(delBtn);
        rightCard.add(addBtn);
        card.add(leftCard);
        card.add(rightCard);

        card.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));

        return card;
    }
}