export class NuevoUsuario {
    nombre!: string;
    nombreUsuario!: string;
    email!: string;
    password!: string;
    authorities: Set<string> = new Set<string>();
    
    constructor(nombre: string, nombreUsuario: string, email: string, password: string, authorities: Set<string> = new Set<string>()) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.authorities = this.authorities;
        
    }
}
