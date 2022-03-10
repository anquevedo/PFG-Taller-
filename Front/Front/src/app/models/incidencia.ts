export class Incidencia {
    id!: number;
    descripcion: string;
    dateInicio: Date;
    dateFin: Date;
    estado: string;
    nombreUsuario: string;

    constructor(descripcion: string, dateInicio: Date, dateFin: Date, estado: string, nombreUsuario: string){
        this.descripcion= descripcion;
        this.dateInicio =dateInicio;
        this.dateFin=dateFin;
        this.estado=estado;
        this.nombreUsuario=nombreUsuario;
    }
}
