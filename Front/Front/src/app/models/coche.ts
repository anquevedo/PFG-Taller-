export class Coche {
    id!: number;
    matricula: string;
    marca: string;
    modelo: string;
    año: number;
    precio: number;

    constructor(matricula: string, marca: string, modelo: string, año: number, precio: number){
        this.matricula= matricula;
        this.marca =marca;
        this.modelo=modelo;
        this.año=año;
        this.precio=precio;
    }
}


