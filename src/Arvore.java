class Arvore implements IArvore{
	No raiz;

    public Arvore(int k){
        raiz = new No(k);
    }

    public void left_rotate(No x){
    	No y = x.dir;
        x.dir = y.esq;
        if(y.esq != null) y.esq.pai = x;
        y.pai = x.pai;
        if(x.pai == null) raiz = y;
        else if(x == x.pai.esq) x.pai.esq = y;
        else x.pai.dir = y;
        y.esq = x;
        x.pai = y;
    }

    public void right_rotate(No x){ //Igual left_rotate, só mudar direito por esquerdo
    	No y = x.esq;
        x.esq = y.dir;
        if(y.dir != null) y.dir.pai = x;
        y.pai = x.pai;
        if(x.pai == null) raiz = y;
        else if(x == x.pai.dir) x.pai.dir = y;
        else x.pai.esq = y;
        y.dir = x;
        x.pai = y;
    }

    public void add(int k){
    	No aux = raiz.busca(k);
        if(k > aux.info) this.addFix(aux.dir = new No(k, aux));
        else if(k < aux.info) this.addFix(aux.esq = new No(k, aux));
    }

    public void addFix(No z){
    	No y;
        while(z.pai != null && z.pai.cor){ //Enquanto a cor for do nodo for vermelha
            if(z.pai == z.pai.pai.esq){
                y = z.pai.pai.dir;
                if(y.cor){ //Se cor de y é vermelha
                    z.pai.cor = false;
                    y.cor = false;
                    z.pai.pai.cor = true;
                    z = z.pai.pai;
                }else{
                    if(z == z.pai.dir){
                        z = z.pai;
                        left_rotate(z);
                    }
                    z.pai.cor = false;
                    z.pai.pai.cor = true;
                    right_rotate(z.pai.pai);
                }
            }else{ //Mesma coisa, só onde inverte direito e esquerdo
                y = z.pai.pai.esq;
                if(y.cor){ //Se cor de y é vermelha
                    z.pai.cor = false;
                    y.cor = false;
                    z.pai.pai.cor = true;
                    z = z.pai.pai;
                }else{
                    if(z == z.pai.esq){
                        z = z.pai;
                        right_rotate(z);
                    }
                    z.pai.cor = false;
                    z.pai.pai.cor = true;
                    left_rotate(z.pai.pai);
                }
            }
        }
        raiz.cor = false;
    }

    public void transplant(No u, No v){
        if(u.pai == null) raiz = v;
        else if(u == u.pai.esq) u.pai.esq = v;
        else u.pai.dir = v;
        if(v != null) v.pai = u.pai;
    }

    public void delete(No z){
    	No y = z, x;
        boolean yCorOriginal = y.cor;
        if(z.esq == null){
            x = z.dir;
            transplant(z, z.dir);
        }else if(z.dir == null){
            x = z.esq;
            transplant(z, z.esq);
        }else{
            y = z.dir.minimo();
            yCorOriginal = y.cor;
            x = y.dir;
            if(y.pai == z) x.pai = y;
            else{
                transplant(y, y.dir);
                y.dir = z.dir;
                y.dir.pai = y;
            }
            transplant(z, y);
            y.esq = z.esq;
            y.esq.pai = y;
            y.cor = z.cor;
        }
        if(yCorOriginal == false){
            delFix(x);
        }
    }

    public void delFix(No x){
    	No w;
        while(x != null && x != raiz && x.cor == false){
            if(x == x.pai.esq){
                w = x.pai.dir;
                if(w.cor){ //W é vermelho ?
                    w.cor = false;
                    x.pai.cor = true;
                    left_rotate(x.pai);
                    w = x.pai.dir;
                }
                if(w.esq.cor == false && w.dir.cor == false){
                    w.cor = true;
                    x = x.pai;
                }else{
                    if(w.dir.cor == false){
                        w.esq.cor = false;
                        w.cor = true;
                        right_rotate(w);
                        w = x.pai.dir;
                    }
                    w.cor = x.pai.cor;
                    x.pai.cor = false;
                    w.dir.cor = false;
                    left_rotate(x.pai);
                    x = raiz;
                }
            }else{
                w = x.pai.esq;
                if(w.cor){ //W é vermelho ?
                    w.cor = false;
                    x.pai.cor = true;
                    right_rotate(x.pai);
                    w = x.pai.esq;
                }
                if(w.dir.cor == false && w.esq.cor == false){
                    w.cor = true;
                    x = x.pai;
                }else{
                    if(w.esq.cor == false){
                        w.dir.cor = false;
                        w.cor = true;
                        left_rotate(w);
                        w = x.pai.esq;
                    }
                    w.cor = x.pai.cor;
                    x.pai.cor = false;
                    w.esq.cor = false;
                    right_rotate(x.pai);
                    x = raiz;
                }
            }
        }
        if(x != null) x.cor = false;
    }

    public No encontra(int k){
    	No aux = raiz.busca(k);
        if(aux.info != k) return null;
        else return aux;
    }

}