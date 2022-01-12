export class Coche {
    id!: number;
    matricula: string;
    marca: string;
    modelo: string;
    anio: number;
    precio: number;

    constructor(matricula: string, marca: string, modelo: string, anio: number, precio: number){
        this.matricula= matricula;
        this.marca =marca;
        this.modelo=modelo;
        this.anio=anio;
        this.precio=precio;
    }
}


