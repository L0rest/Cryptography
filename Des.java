import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class Des {

    final int TAILLE_BLOC = 64;
    final int TAILLE_SOUS_BLOC = 32;
    final int NB_RONDE = 1;

    final int[] TAB_DECALAGE = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    final int[] PERM_INITIALE = {
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7,
            56, 48, 40, 32, 24, 16, 8, 0,
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6
    };

    final int[] PC1 = {
            56, 48, 40, 32, 24, 16, 8,
            0, 57, 49, 41, 33, 25, 17,
            9, 1, 58, 50, 42, 34, 26,
            18, 10, 2, 59, 51, 43, 35,
            62, 54, 46, 38, 30, 22, 14,
            6, 61, 53, 45, 37, 29, 21,
            13, 5, 60, 52, 44, 36, 28,
            20, 12, 4, 27, 19, 11, 3
    };

    final int[] PC2 = {
            13, 16, 10, 23, 0, 4,
            2, 27, 14, 5, 20, 9,
            22, 18, 11, 3, 25, 7,
            15, 6, 26, 19, 12, 1,
            40, 51, 30, 36, 46, 54,
            29, 39, 50, 44, 32, 47,
            43, 48, 38, 55, 33, 52,
            45, 41, 49, 35, 28, 31
    };

    final int[][][] S = {
            {
                    {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
            },
            {
                    {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
            },
            {

                    {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 6, 10, 14, 7},
                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
            },
            {
                    {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
            },
            {
                    {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 6}
            },
            {
                    {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
            },
            {
                    {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
            },
            {
                    {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
            }
    };

    final int[] E = {
            31, 0, 1, 2, 3, 4,
            3, 4, 5, 6, 7, 8,
            7, 8, 9, 10, 11, 12,
            11, 12, 13, 14, 15, 16,
            15, 16, 17, 18, 19, 20,
            19, 20, 21, 22, 23, 24,
            23, 24, 25, 26, 27, 28,
            27, 28, 29, 30, 31, 0
    };

    final int[] P = {
            15, 6, 19, 20, 28, 11, 27, 16,
            0, 14, 22, 25, 4, 17, 30, 9,
            1, 7, 23, 13, 31, 26, 2, 8,
            18, 12, 29, 5, 21, 10, 3, 24
    };

    public int[] masterKey;
    public int[][] tab_cles;
    public Random rand = new Random();

    public Des() {
        this.masterKey = new int[64];

        for (int i = 0; i < 64; i++) {
            this.masterKey[i] = rand.nextInt(2);
        }

        this.tab_cles = new int[16][];
    }

    public int[] stringToBits(String message) {
        String bits = new BigInteger(message.getBytes()).toString(2);

        int[] block = new int[bits.length()];

        for (int i = 0; i < bits.length(); i++) {
            block[i] = Integer.parseInt(String.valueOf(bits.charAt(i)));
        }

        if (block.length % 64 != 0) {
            int[] block_tmp = new int[block.length + (64 - (block.length % 64))];

            System.arraycopy(block, 0, block_tmp, 0, block.length);

            block = block_tmp;
        }

        return block;
    }

    public String bitsToString(int[] block) {
        StringBuilder s = new StringBuilder();

        for (int j : block) {
            s.append(j);
        }

        return new String(new BigInteger(s.toString(), 2).toByteArray());
    }

    public int[] generePermutation(int taille) {
        int[] permutation = new int[taille];

        for (int i = 0; i < taille; i++) {
            permutation[i] = i;
        }

        for (int i = 0; i < taille; i++) {
            int j = rand.nextInt(taille);
            int tmp = permutation[i];
            permutation[i] = permutation[j];
            permutation[j] = tmp;
        }

        return permutation;
    }

    public int[] genereMasterKey() {
        int[] masterKey = new int[64];

        for (int i = 0; i < 64; i++) {
            masterKey[i] = rand.nextInt(2);
        }

        return masterKey;
    }

    public int[] permutation(int[] tab_permutation, int[] bloc) {
        int[] bloc_permute = new int[tab_permutation.length];

        for (int i = 0; i < tab_permutation.length; i++) {
            bloc_permute[i] = bloc[tab_permutation[i]];
        }

        return bloc_permute;
    }

    public int[] invPermutation(int[] tab_permutation, int[] bloc) {
        int[] bloc_permute = new int[tab_permutation.length];

        for (int i = 0; i < tab_permutation.length; i++) {
            bloc_permute[tab_permutation[i]] = bloc[i];
        }

        return bloc_permute;
    }

    public int[][] decoupage(int[] bloc, int tailleBlocs) {
        if (bloc.length % tailleBlocs != 0)
            throw new IllegalArgumentException("Le tableau de blocs doit être divisible par la taille des blocs");

        int nbBlocs = bloc.length / tailleBlocs;
        int[][] blocs = new int[nbBlocs][tailleBlocs];

        for (int i = 0; i < nbBlocs; i++) {
            System.arraycopy(bloc, i * tailleBlocs, blocs[i], 0, tailleBlocs);
        }

        return blocs;
    }

    public int[] recollage_bloc(int[][] blocs) {
        if (blocs.length == 0) throw new IllegalArgumentException("Le tableau de blocs ne doit pas être vide");

        int[] bloc = new int[blocs.length * blocs[0].length];

        for (int i = 0; i < blocs.length; i++) {
            System.arraycopy(blocs[i], 0, bloc, i * blocs[i].length, blocs[i].length);
        }

        return bloc;
    }

    public int[] decale_gauche(int[] bloc, int nbCran) {
        int[] bloc_decale = new int[bloc.length];

        System.arraycopy(bloc, nbCran, bloc_decale, 0, bloc.length - nbCran);
        System.arraycopy(bloc, 0, bloc_decale, bloc.length - nbCran, nbCran);

        return bloc_decale;
    }

    public int[] xor(int[] tab1, int[] tab2) {
        if (tab1.length != tab2.length)
            throw new IllegalArgumentException("Les deux tableaux doivent avoir la même taille");

        int[] tab_xor = new int[tab1.length];

        for (int i = 0; i < tab1.length; i++) {
            tab_xor[i] = tab1[i] ^ tab2[i];
        }

        return tab_xor;
    }

    public void genereCle(int n) {
        int[] Kn = permutation(PC1, masterKey);

        int[][] Kn_blocs = decoupage(Kn, Kn.length / 2);

        Kn_blocs[0] = decale_gauche(Kn_blocs[0], TAB_DECALAGE[n]);
        Kn_blocs[1] = decale_gauche(Kn_blocs[1], TAB_DECALAGE[n]);

        Kn = recollage_bloc(Kn_blocs);

        tab_cles[n] = permutation(PC2, Kn);

    }

    public int[] fonction_S(int[] tab, int noRonde) {
        String row = tab[0] + "" + tab[5];
        String col = tab[1] + "" + tab[2] + tab[3] + tab[4];

        int tab_s = S[noRonde][Integer.parseInt(row, 2)][Integer.parseInt(col, 2)];

        String tab_s_bin = Integer.toBinaryString(tab_s);

        int[] tab_s_bin_int = new int[tab_s_bin.length()];

        for (int i = 0; i < tab_s_bin.length(); i++) {
            tab_s_bin_int[i] = Integer.parseInt(String.valueOf(tab_s_bin.charAt(i)));
        }

        if (tab_s_bin_int.length < 4) {
            int[] tab_s_bin_int_tmp = new int[4];

            System.arraycopy(tab_s_bin_int, 0, tab_s_bin_int_tmp, 4 - tab_s_bin_int.length, tab_s_bin_int.length);

            tab_s_bin_int = tab_s_bin_int_tmp;
        }

        return tab_s_bin_int;
    }

    int[] fonction_F(int[] uneCle, int[] unD) {
        int[] E_D = permutation(E, unD);

        int[] E_D_xor_K = xor(E_D, uneCle);

        int[][] E_D_xor_K_blocs = decoupage(E_D_xor_K, 6);

        int[][] E_D_xor_K_blocs_S = new int[E_D_xor_K_blocs.length][];

        for (int i = 0; i < E_D_xor_K_blocs.length; i++) {
            E_D_xor_K_blocs_S[i] = fonction_S(E_D_xor_K_blocs[i], i);
        }

        int[] E_D_xor_K_blocs_S_recollage = recollage_bloc(E_D_xor_K_blocs_S);

        return permutation(P, E_D_xor_K_blocs_S_recollage);
    }

    public int[] crypte(String message_clair) {
        // Convert message to bits
        int[] message_code = stringToBits(message_clair);

        // Split message into blocks of 64 bits
        int[][] message_code_blocs = decoupage(message_code, TAILLE_BLOC);

        // For each block, make an initial permutation and split it into two halves
        for (int i = 0; i < message_code_blocs.length; i++) {
            int[] bloc = permutation(PERM_INITIALE, message_code_blocs[i]);

            int[][] sous_blocs = decoupage(bloc, bloc.length / 2);

            for (int j = 0; j < 16; j++) {
                genereCle(j);

                int[] tmp = xor(sous_blocs[0], fonction_F(tab_cles[j], sous_blocs[1]));
                sous_blocs[0] = sous_blocs[1];
                sous_blocs[1] = tmp;
            }

            bloc = recollage_bloc(sous_blocs);

            message_code_blocs[i] = invPermutation(PERM_INITIALE, bloc);

        }

        return recollage_bloc(message_code_blocs);

    }

    public String decrypte(int[] message_code) {
        // Split message into blocks of 64 bits
        int[][] message_code_blocs = decoupage(message_code, TAILLE_BLOC);

        // For each block, make an initial permutation and split it into two halves
        for (int i = 0; i < message_code_blocs.length; i++) {
            int[] bloc = permutation(PERM_INITIALE, message_code_blocs[i]);

            int[][] sous_blocs = decoupage(bloc, bloc.length / 2);

            for (int j = 15; j >= 0; j--) {
                int[] tmp = xor(sous_blocs[0], fonction_F(tab_cles[j], sous_blocs[1]));
                sous_blocs[0] = sous_blocs[1];
                sous_blocs[1] = tmp;
            }

            bloc = recollage_bloc(sous_blocs);

            message_code_blocs[i] = invPermutation(PERM_INITIALE, bloc);

        }

        return bitsToString(recollage_bloc(message_code_blocs));
    }


    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int j : masterKey) {
            s.append(j);
        }

        return s.toString();
    }

}