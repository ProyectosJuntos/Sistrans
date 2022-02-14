import datetime
import random
import names
from time import process_time

from random_word import RandomWords
r = RandomWords()


tipopersona = [", 'Natural', ", ", 'Juridica', "]
docs = ["CC", "CI", "TP", "CE"]
dir1 = ["Cl ", "Kr ", "Dg ", "Tr "]
dir2 = ["sur ", "a ", "b ", "c ", "d ", "e ", "f ", "g ",
        "h ", "i ", "j ", "k ", "l ", " ", " ", " ", " ", " ", " ", " "]
depar = [
    "'Amazonas', 'Leticia'",
    "'Antioquia', 'Medellin'",
    "'Arauca', 'Arauca'",
    "'Atlantico', 'Barranquilla'",
    "'Bolivar', 'Cartagena de Indias'",
    "'Boyaca', 'Tunja'",
    "'Caldas', 'Manizales'",
    "'Caqueta', 'Florencia'",
    "'Casanare', 'Yopal'",
    "'Cauca', 'Popayan'",
    "'Cesar', 'Valledupar'",
    "'Choco', 'Quibdo'",
    "'Cordoba', 'Monteria'",
    "'Cundinamarca', 'Bogota'",
    "'Guainia', 'Inirida'",
    "'Guaviare', 'San Jose del Guaviare'",
    "'Huila', 'Neiva'",
    "'LaGuajira', ' Riohacha'",
    "'Magdalena', 'Santa Marta'",
    "'Meta', 'Villavicencio'",
    "'Nariño', 'San Juan de Pasto'",
    "'Nortede Santander', ' San Jose de Cucuta'",
    "'Putumayo', 'Mocoa'",
    "'Quindio', 'Armenia'",
    "'Risaralda', 'Pereira'",
    "'SanAndres y Providencia', ' San Andres'",
    "'Santander', 'Bucaramanga'",
    "'Sucre', 'Sincelejo'",
    "'Tolima', 'Ibague'",
    "'Valle del Cauca', ' Cali'",
    "'Vaupes', 'Mitu'",
    "'Vichada', 'Puerto Carreño'"]
empl = ["Cajero",
        "Gerente oficina"]
puesto = ["Oficina",
          "ATM",
          "Web",
          "Movil"]
tipoCuenta = ["Ahorros", "Corriente"]

juridicos = []
productosJuridicos = []
cuentasJuridicas = []
idUsuarios = []
idUsuariosDisp = []
idGeroficina = []
idCajeros = []
idCajerosPA = []
idoficinas = []
idPrestamos = []
idProductos = []
idCuentasActivas = []
idCuentasDesactivadas = []
idProductosDisponibles = []
puntosA = {"Oficina": [],
           "ATM": [],
           "Web": [],
           "Movil": []}
productosConUsuarios = {}
cuentasConUsuarios = {}

rangoUsuarios = 30000
rangoEmpleados = int(rangoUsuarios * 0.6)
# rangoOficinas = Depende del número de empleados
# rangoPAtencion (en oficina) = Depende del numero de empleados
rangoPAtencion = 150000
rangoProductos = 300000
rangoCuentas = 150000
# rangoPrestamos = Depende de los productos que quedan
# rangoCuentasJuridicas = Depende de los productos que quedan
rangoOperaciones = 185000

# Esto genera ~13000
# rangoUsuarios = 100
# rangoEmpleados = int(rangoUsuarios * 0.6)
# # rangoOficinas = Depende del número de empleados
# # rangoPAtencion (en oficina) = Depende del numero de empleados
# rangoPAtencion = 500
# rangoProductos = 1000
# rangoCuentas = 5000
# # rangoPrestamos = Depende de los productos que quedan
# # rangoCuentasJuridicas = Depende de los productos que quedan
# rangoOperaciones = 10000


# rangoUsuarios = 100
# rangoEmpleados = int(rangoUsuarios * 0.6)
# # rangoOficinas = Depende del número de empleados
# # rangoPAtencion (en oficina) = Depende del numero de empleados
# rangoPAtencion = 500
# rangoProductos = 100
# rangoCuentas = 50
# # rangoPrestamos = Depende de los productos que quedan
# # rangoCuentasJuridicas = Depende de los productos que quedan
# rangoOperaciones = 100

palabrasG = ['Class-size', 'annoyed', 'multivalued', 'nationhood', 'indifference', 'vanishes', 'terebinths', 'None', 'flowchart', 'solars', 'hongs', 'dailies', 'ionist', 'assimilation', 'metacarpi', 'oligodendroglia', 'spiza', 'warrioress', 'multibillion', 'fulgurites', 'instructers', 'carum', 'greyly', 'nephrology', 'motet', 'El Ferrol', 'tub-wheel', 'tonality', 'egg-blower', 'unsuspectingly', 'preceptress', 'schlumpy', 'synonymize', 'bestowals', 'paludial', 'bibliophagist', 'fish-bolt', 'contemplating', 'moulds', 'neo-Pantheism', 'unruptured', 'Little Dipper', 'comptible', 'albarello', 'annuli', 'decahydrate', 'tetroxid', 'veronal', 'toadyish', 'cardiopathic', 'profet', 'earthwards', 'groser', 'homogenously', 'pocket', 'sauce-alone', 'violet-tinted', 'laqueary', 'six-pack', 'spitchcocked', 'stepping-point', 'announcements', 'uniformise', 'Silliman', 'sea-cliff', 'variation', 'felsic', 'rigging-screws', 'Majorcan', 'heaviness', 'self-repelling', 'regelation', 'trinary', 'tub-wheel', 'panda', 'nonguided', 'nanometer', 'kent-tackle', 'macroregional', 'cross-motion', 'rawr', 'erotism', 'subsolid', 'marvered', 'microcytes', 'campervan', 'revolutionalise', 'ignosticism', 'Jefferson', 'uncia', 'disseat', 'dreamed', 'fiorino', 'smokes', 'gato', 'skunking', 'canicule', 'Marcosian', 'transitory', 'softback', 'unheart', 'Budapest', 'unswear', 'unhumanize', 'diversions', 'dispossessions', 'heyducs', 'glistened', 'lamp-posts', 'whodunits', 'kickboxer', 'firmness', 'deviationism', 'adrenarche', 'coalition', 'funnily', 'withholdings', 'malefic', 'busks', 'peerdom', 'vibraphone', 'assectation', 'grappler', 'reken',
             'cyrilla', 'queried', 'postdose', 'zikkurat', 'epileptoid', 'dialectologist', 'drapey', 'sensitivities', 'snarfle', 'sequenced', 'wood-charcoal', 'abbeys', 'farnesene', 'terranes', 'stater', 'synclinal', 'gownsman', 'carpet-baggery', '32-bit', 'riever', 'validators', 'acumen', 'trass', 'nerve-wracking', 'gillie', 'wraithe', 'honbasho', 'platitude', 'overwet', 'A-gay', 'bluewash', 'adulterine', 'serpentlike', 'laryngologist', 'nowise', 'squabash', 'flinchy', 'micronations', 'renounced', 'misinform', 'twirling', 'trolly', 'sifting', 'dealing', 'cerulean', 'forlive', 'telemotor', 'assicons', 'gradualistic', 'causeuse', 'intervert', 'wheeling', 'demonetises', 'sweepage', 'dedicatee', 'squishier', 'nephew', 'jiminy', 'rusticator', 'engrosseth', 'conylene', 'rufescent', 'shorten', 'romance', 'Panasonic', 'multiwarhead', 'Duke of Windsor', 'emolliate', 'passerine', 'polyproline', 'dotard', 'valanche', 'reflectances', 'córdoba', 'eftsoons', 'royal-yard']
nombreG = ['Frederick', 'Mary', 'Mark', 'Jeremy', 'Christina', 'Patricia', 'Frank', 'Alta', 'Brigitte', 'Nell', 'Maria', 'Sabrina', 'Mary', 'Marie', 'David', 'Paul', 'John', 'Dana', 'Jeffrey', 'Leonard', 'Jose', 'James', 'Mark', 'Kimberly', 'Audrey', 'Steve', 'John', 'Jeff', 'Barbara', 'Vivian', 'Janet', 'Nancy', 'Mary', 'Charles', 'David', 'Jasper', 'Michele', 'Jason', 'Violet', 'Camila', 'Juliana', 'Donald', 'Lolita', 'Maria', 'Joel', 'Connie', 'Joanne', 'Phillip', 'Cheryl', 'Kenneth', 'Lasonya', 'Bradley', 'Toby', 'Richard', 'Ryan', 'Anna', 'Juan', 'Vivian', 'Mark', 'Leland', 'Glenda', 'Sanford', 'John', 'Rickey', 'Betty', 'Martin', 'Connie', 'Foster', 'Geoffrey', 'Jose', 'Cleo', 'Ruth', 'Donald', 'Jessica', 'James', 'Anthony', 'Derrick', 'Lauren', 'Barbara', 'Camilla', 'Kristi', 'Christine', 'Michael', 'Ann', 'William', 'James', 'Henry', 'Bruce', 'Maria', 'Pamella', 'Alan', 'Donald', 'Richard', 'Edward', 'Charles', 'Marc', 'Howard', 'Gerald', 'Betty',
           'Viola', 'Kim', 'Delores', 'Robert', 'Kathryn', 'Pauline', 'Louis', 'James', 'Patrick', 'Leonila', 'Jarred', 'Michael', 'Paul', 'Thomas', 'Bruce', 'Robert', 'Octavio', 'Angela', 'Karen', 'Susan', 'Joseph', 'Homer', 'Esther', 'Edward', 'Toni', 'Columbus', 'Omer', 'Samuel', 'Amber', 'Michael', 'Ruth', 'Henry', 'Carlos', 'Marsha', 'Milton', 'Autumn', 'Aaron', 'Chad', 'Robert', 'Sergio', 'Charles', 'Sherri', 'Lucille', 'Brian', 'William', 'Jeanie', 'Gary', 'Shawn', 'Carrie', 'Katherine', 'Lewis', 'Lisa', 'Annie', 'Lu', 'Mark', 'Sidney', 'Dorothea', 'Adam', 'Luis', 'Edna', 'Jane', 'Blake', 'Jan', 'Julian', 'James', 'Mary', 'Allison', 'Dean', 'Connie', 'Kim', 'Marie', 'Phil', 'Valerie', 'Ronald', 'Mark', 'Annie', 'Bonnie', 'Manuel', 'Lawrence', 'Elizabeth', 'Kenneth', 'Tony', 'James', 'Gregory', 'Robert', 'Shanda', 'Hector', 'Shelia', 'Edna', 'Nelson', 'Ernesto', 'Jason', 'Justin', 'Nicole', 'Micheal', 'Bradford', 'Charles', 'Mary', 'Norbert', 'Tamika', 'John']
apellidosG = ['Mcguire', 'Dollard', 'Mccormack', 'Ward', 'Olson', 'Gandy', 'Woods', 'Griffin', 'Nolin', 'Scott', 'Stallworth', 'Large', 'Harris', 'Young', 'Warren', 'Weber', 'Witcher', 'Peterson', 'Flowers', 'Sisco', 'Mooney', 'Obrien', 'Hernandez', 'Bell', 'Landry', 'White', 'Ingersoll', 'Crowley', 'Rose', 'Clark', 'Canez', 'Bussey', 'Stevens', 'Lewis', 'Oakley', 'Beck', 'Mansfield', 'Pederson', 'Johnston', 'Mitchell', 'Clark', 'Landeros', 'Hawn', 'Sappington', 'Whitman', 'Klaus', 'Wheeler', 'Riggs', 'Kaster', 'Walker', 'Mick', 'Deaton', 'Johnson', 'Havenhill', 'Kinter', 'Bundy', 'Mcfadden', 'Sarvas', 'Kyle', 'Elmquist', 'Cabrera', 'Black', 'Dunn', 'Avans', 'Amin', 'Honn', 'Cartwright', 'Gallant', 'Mullis', 'Richards', 'Randolph', 'Gomez', 'Dion', 'Johnson', 'Jackson', 'Davis', 'Ditch', 'Jones', 'Alverez', 'Perkins', 'Brown', 'Stearns', 'Hall', 'Ross', 'Martin', 'Jackson', 'Lewis', 'Goad', 'Hubbard', 'Hillyer', 'Ranieri', 'Deeter', 'Ledesma', 'Osborn', 'Shumay', 'Godwin', 'Miller', 'Hood', 'Clancy', 'Uribe', 'Hague', 'Irwin', 'Santamaria', 'Derck', 'Daugherty', 'Crocker', 'Partin', 'Gionfriddo', 'Mcguinness', 'Fernandez', 'Loder', 'Doran', 'Washington', 'Day', 'Stackhouse', 'Hall', 'Squire', 'Crasco', 'Yang', 'Ochoa', 'Brady', 'Ramirez', 'Noboa', 'Alvarado', 'Schoppert', 'Manalo', 'Webb', 'Bickford', 'Mosely', 'Cluff', 'Otey', 'Latimer', 'Keeling', 'Hall', 'Gonzalez', 'Smith', 'Olin', 'Walsh', 'Farley', 'Watkins', 'Uppencamp', 'Hernandez', 'Peterson', 'Scott', 'Armor', 'Mcdowell', 'Williams', 'Amos', 'Strobel', 'Henke', 'Terracina', 'Hutchinson', 'Ong', 'Gajeski', 'Chou', 'Jones', 'Edwards', 'Beach', 'Mccoard', 'Aparicio', 'Wrubel', 'Turner', 'Thompson', 'Reed', 'Stanford', 'Smith', 'Delorenzo', 'Mcmullen', 'Ellis', 'Stinson', 'Smith', 'Pinks', 'Huang', 'Kolb', 'Harrison', 'Kramer', 'Hohman', 'Fess', 'Vega', 'Watts', 'Carlson', 'Deubner', 'Sotello', 'Aguilera', 'Saldivar', 'Radune',
              'Kelley', 'Bradt', 'Grubbs', 'Ritter', 'Fisher', 'Downey', 'Salas', 'Treadway', 'Eubanks', 'Lipsitz', 'Hassell', 'Palmer', 'Muir', 'Wolfe']


def palabras():
    print("Generando palabras...")
    t_i = process_time()
    for i in range(200):
        a = str(r.get_random_word()).replace(
            "'", "-").replace("%", "").replace("&", "")
        palabrasG.append(a)
        if i % 10 == 0:
            print(str(i))
    t_f = process_time()
    print('Palabras en: ' + str(t_f - t_i) + 's')
    print(palabrasG)


def nombres():
    print("Generando nombres...")
    t_i = process_time()
    for i in range(200):
        a = names.get_first_name()
        nombreG.append(a)
        if i % 10 == 0:
            print(str(i))
    t_f = process_time()
    print('Nombres en: ' + str(t_f - t_i) + 's')
    print(nombreG)


def apellidos():
    print("Generando apellidos...")
    t_i = process_time()
    for i in range(200):
        a = names.get_last_name()
        apellidosG.append(a)
        if i % 10 == 0:
            print(str(i))
    t_f = process_time()
    print('Apellidos en: ' + str(t_f - t_i) + 's')
    print(apellidosG)


def palabra():
    return random.choice(palabrasG)


def unNombre():
    return random.choice(nombreG) + " " + random.choice(apellidosG)


def usuarios():
    t_i = process_time()
    tuplasCreadas = 0
    file = open("b_usuario.sql", "w", encoding="utf-8")
    for i in range(rangoUsuarios):
        seq = "INSERT INTO b_usuario VALUES ("
        doc = random.choice(docs)
        tipo = random.choice(tipopersona)
        if tipo == ", 'Juridica', ":
            juridicos.append("'" + doc + "', " + str(i))
        seq += "'" + doc + "', " + \
            str(i) + tipo + str(i) + ", " + str(i) + ", "
        idUsuarios.append("'" + doc + "', " + str(i))
        idUsuariosDisp.append("'" + doc + "', " + str(i))
        seq += "'" + unNombre() + "',"
        seq += "'Colombia', "
        seq += "'" + random.choice(dir1) + str(random.randint(1, 99)) + random.choice(dir2) + "#" + str(
            random.randint(1, 99)) + random.choice(dir2).strip() + "-" + str(random.randint(1, 99)) + "', "
        seq += "'" + str(i) + palabra() + "@" + palabra() + ".com', "
        seq += "'" + str(random.randint(3000000000, 3999999999)) + "', "
        seq += random.choice(depar) + ", "
        seq += "'" + str(random.randint(100000, 990000)) + "'"
        file.write(seq + ");\n")
        # tuplasCreadas += 1
        tuplasCreadas += 1
        if tuplasCreadas % 500 == 0:
            print("Usuarios: " + str(tuplasCreadas))

    file.close()
    print("Usuarios generados")
    print(str(tuplasCreadas))
    t_f = process_time()
    print('Procesado en: ' + str(t_f - t_i) + 's')


def empleados():
    t_i = process_time()
    tuplasCreadas = 0
    file = open("b_empleado.sql", "w", encoding="utf-8")
    for i in range(rangoEmpleados):
        seq = "INSERT INTO b_empleado VALUES ("
        ide = random.choice(idUsuariosDisp)
        idUsuariosDisp.remove(ide)
        seq += ide + ", "
        # doc = random.choice(docs)
        # seq += "'" + doc + "', " + \
        #     str(i) + ", "
        tipo = str(random.choice(empl))
        if tipo == "Gerente oficina":
            idGeroficina.append(ide)
        if tipo == "Cajero":
            idCajeros.append(ide)
            idCajerosPA.append(ide)
        seq += "'" + tipo + "', "
        file.write(seq + "null, null);\n")
        tuplasCreadas += 1
        if tuplasCreadas % 500 == 0:
            print("Empleados: " + str(tuplasCreadas))
    file.close()
    print("Empleados generados")
    print(str(tuplasCreadas))
    t_f = process_time()
    print('Procesado en: ' + str(t_f - t_i) + 's')


def oficinas():
    t_i = process_time()
    tuplasCreadas = 0
    file = open("b_oficina.sql", "w", encoding="utf-8")
    for i in range(len(idGeroficina)):
        seq = "INSERT INTO b_oficina VALUES ("
        seq += str(i) + ", "
        seq += "'" + palabra() + "', "
        seq += "'" + random.choice(dir1) + str(random.randint(1, 99)) + random.choice(dir2) + "#" + str(
            random.randint(1, 99)) + random.choice(dir2).strip() + "-" + str(random.randint(1, 99)) + "', "
        seq += str(random.randint(1, 99)) + ", "
        try:
            idGerO = idGeroficina[i]
            seq += str(idGerO)
            file.write(seq + ");\n")
            try:
                file.write("UPDATE B_EMPLEADO SET IDOFICINA= " +
                           str(i) + " WHERE tipoID =" + idGerO.split(", ")[0] + " AND id=" + idGerO.split(", ")[1] + ";\n")
                idoficinas.append(str(i))
                # tuplasCreadas += 1
            except Exception:
                print("algo falló aaª")
        except Exception:
            print("algo falló agregando oficinas")
            pass
        tuplasCreadas += 1
        if tuplasCreadas % 500 == 0:
            print("Oficinas: " + str(tuplasCreadas))

    file.close()
    print("Oficinas generadas")
    print(str(tuplasCreadas))
    t_f = process_time()
    print('Procesado en: ' + str(t_f - t_i) + 's')


def pAtencion():
    t_i = process_time()
    tuplasCreadas = 0
    file = open("b_puestoatencion.sql", "w", encoding="utf-8")

    for i in range(len(idCajerosPA)-1):
        seq = "INSERT INTO b_puestoatencion VALUES ("
        seq += str(i) + ", "
        p = "Oficina"
        seq += "'" + p + "', "

        lista = puntosA[p]
        lista.append(str(i))

        seq += "'" + random.choice(dir1) + str(random.randint(1, 99)) + random.choice(dir2) + "#" + str(
            random.randint(1, 99)) + random.choice(dir2).strip() + "-" + str(random.randint(1, 99)) + "', "
        idCaj = idCajerosPA[i]
        seq += str(idCaj) + ", "
        seq += random.choice(idoficinas)
        file.write(seq + ");\n")
        file.write("UPDATE B_EMPLEADO SET idpuestoatencion= " +
                   str(i) + " WHERE tipoId=" + idCaj.split(", ")[0] + " AND id=" + idCaj.split(", ")[1] + ";\n")
        tuplasCreadas += 1
        if tuplasCreadas % 500 == 0:
            print("PAtencion: " + str(tuplasCreadas))

    for i in range(len(idCajerosPA), rangoPAtencion):
        seq = "INSERT INTO b_puestoatencion VALUES ("
        seq += str(i) + ", "
        p = random.choice(["ATM", "Web", "Movil"])
        seq += "'" + p + "', "

        lista = puntosA[p]
        lista.append(str(i))

        if p == "ATM":
            seq += "'" + random.choice(dir1) + str(random.randint(1, 99)) + random.choice(dir2) + "#" + str(
                random.randint(1, 99)) + random.choice(dir2).strip() + "-" + str(random.randint(1, 99)) + "', "
            seq += "null, null, "
            seq += random.choice(idoficinas)
            file.write(seq + ");\n")
        else:
            seq += "'" + p + "', null, null, null"
            file.write(seq + ");\n")
        tuplasCreadas += 1
        if tuplasCreadas % 500 == 0:
            print("PAtencion: " + str(tuplasCreadas))
    file.close()
    print("Puestos de atencion generados")
    print(str(tuplasCreadas))
    t_f = process_time()
    print('Procesado en: ' + str(t_f - t_i) + 's')


def productos():
    t_i = process_time()
    tuplasCreadas = 0
    file = open("b_producto.sql", "w", encoding="utf-8")
    for i in range(rangoProductos):
        seq = "INSERT INTO b_producto VALUES ("
        seq += str(i) + ", "
        estado = random.choice(
            ["'Activa', ", "'Activa', ", "'Activa', ", "'Activa', ", "'Activa', ", "'Activa', ", "'Cerrada', ", "'Desactivada', "])
        seq += estado
        if estado == "'Activa', ":
            seq += str(random.randint(1, 1000000000)) + ", "
            idProductos.append(str(i))
            idProductosDisponibles.append(str(i))
        elif estado == "'Cerrada', ":
            seq += "0, "
            idPrestamos.append(str(i))
        else:
            seq += "0, "
            idCuentasDesactivadas.append(str(i))

        start_date = datetime.date(1999, 1, 1)
        end_date = datetime.date(2021, 12, 3)

        time_between_dates = end_date - start_date
        days_between_dates = time_between_dates.days
        random_number_of_days = random.randrange(days_between_dates)
        random_date = start_date + \
            datetime.timedelta(days=random_number_of_days)

        start_date1 = datetime.date(1999, 1, 1)
        end_date1 = datetime.date(2021, 12, 3)

        time_between_dates1 = end_date1 - start_date1
        days_between_dates1 = time_between_dates1.days
        random_number_of_days1 = random.randrange(days_between_dates1)
        random_date1 = start_date1 + \
            datetime.timedelta(days=random_number_of_days1)

        if (random_date1-random_date).days > 0:
            seq += "TO_TIMESTAMP('" + random_date.strftime("%Y-%m-%d %H:%M:%S.%f") + "', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('" + \
                random_date1.strftime("%Y-%m-%d %H:%M:%S.%f") + \
                "', 'YYYY-MM-DD HH24:MI:SS.FF'), "
        else:
            seq += "TO_TIMESTAMP('" + random_date1.strftime("%Y-%m-%d %H:%M:%S.%f") + "', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('" + \
                random_date.strftime("%Y-%m-%d %H:%M:%S.%f") + \
                "', 'YYYY-MM-DD HH24:MI:SS.FF'), "

        seq += random.choice(idoficinas) + ", "
        idUsuario = random.choice(idUsuarios)
        productosConUsuarios[str(i)] = idUsuario

        seq += idUsuario

        if estado == "'Activa', " and juridicos.count(idUsuario) == 1:
            productosJuridicos.append(str(i))

        file.write(seq + ");\n")
        tuplasCreadas += 1
        if tuplasCreadas % 500 == 0:
            print("Productos: " + str(tuplasCreadas))
    file.close()
    print("Productos generados")
    print(str(tuplasCreadas))
    t_f = process_time()
    print('Procesado en: ' + str(t_f - t_i) + 's')


def cuentas():
    t_i = process_time()
    tuplasCreadas = 0
    file = open("b_cuenta.sql", "w", encoding="utf-8")
    for i in range(int(len(idProductosDisponibles) * 0.5)):
        seq = "INSERT INTO b_cuenta VALUES ("
        ide = idProductosDisponibles[i]
        if productosJuridicos.count(ide) != 0:
            cuentasJuridicas.append(ide)
        idProductosDisponibles.remove(ide)
        idCuentasActivas.append(ide)
        seq += str(ide) + ", "
        seq += "'" + random.choice(tipoCuenta) + "'"
        file.write(seq + ");\n")

        if ide in productosConUsuarios.keys():
            cuentasConUsuarios[ide] = productosConUsuarios[ide]
        tuplasCreadas += 1
        if tuplasCreadas % 500 == 0:
            print("Cuentas: " + str(tuplasCreadas))

    for i in range(len(idCuentasDesactivadas)-1):
        seq = "INSERT INTO b_cuenta VALUES ("
        ide = idCuentasDesactivadas[i]
        seq += str(ide) + ", "
        seq += "'" + random.choice(tipoCuenta) + "'"
        file.write(seq + ");\n")

        if ide in productosConUsuarios.keys():
            cuentasConUsuarios[ide] = productosConUsuarios[ide]
        tuplasCreadas += 1
        if tuplasCreadas % 500 == 0:
            print("Cuentas: " + str(tuplasCreadas))
    file.close()
    print("Cuentas generadas")
    print(str(tuplasCreadas))
    t_f = process_time()
    print('Procesado en: ' + str(t_f - t_i) + 's')


def prestamos():
    t_i = process_time()
    tuplasCreadas = 0
    file = open("b_prestamo.sql", "w", encoding="utf-8")
    for i in range(len(idPrestamos)):
        seq = "INSERT INTO b_prestamo VALUES ("
        ide = random.choice(idPrestamos)
        idPrestamos.remove(ide)
        seq += str(ide) + ", "
        seq += str(random.randint(1, 1000000000))
        file.write(seq + ");\n")
        tuplasCreadas += 1
        if tuplasCreadas % 500 == 0:
            print("Prestamos: " + str(tuplasCreadas))
    for i in range(random.randint(1, len(idProductosDisponibles))):
        seq = "INSERT INTO b_prestamo VALUES ("
        ide = random.choice(idProductosDisponibles)
        idProductosDisponibles.remove(ide)
        seq += str(ide) + ", "
        seq += str(random.randint(1, 1000000000))
        file.write(seq + ");\n")
        tuplasCreadas += 1
        if tuplasCreadas % 500 == 0:
            print("Prestamos: " + str(tuplasCreadas))
    file.close()
    print("Prestamos generadas")
    print(str(tuplasCreadas))
    t_f = process_time()
    print('Procesado en: ' + str(t_f - t_i) + 's')


def b_cuentasJuridicas():
    t_i = process_time()
    tuplasCreadas = 0
    file = open("b_cuentajuridica.sql", "w", encoding="utf-8")
    for i in range(len(idProductosDisponibles)):
        seq = "INSERT INTO b_cuentajuridica VALUES ("
        seq += str(i) + ", "
        seq += str(random.randint(1, 1000000000)) + ", "
        seq += random.choice(["'Mensual', ", "'Quincenal', "])
        ide = random.choice(cuentasJuridicas)
        seq += str(ide)
        file.write(seq + ");\n")
        tuplasCreadas += 1
        if tuplasCreadas % 500 == 0:
            print("CuentasJ: " + str(tuplasCreadas))

        seq = "INSERT INTO b_cuentajuridica_cuenta VALUES ("
        seq += str(i) + ", "
        seq += random.choice(idCuentasActivas)
        file.write(seq + ");\n")
        tuplasCreadas += 1
        if tuplasCreadas % 500 == 0:
            print("CuentasJ: " + str(tuplasCreadas))
    file.close()
    print("Cuentas juridicas generadas")
    print(str(tuplasCreadas))
    t_f = process_time()
    print('Procesado en: ' + str(t_f - t_i) + 's')


def operaciones():
    t_i = process_time()
    tuplasCreadas = 0
    file = open("b_operacion.sql", "w", encoding="utf-8")
    for i in range(rangoOperaciones):
        seq = "INSERT INTO b_operacion VALUES ("
        seq += str(i) + ", "
        tipoop = random.choice(
            ["'Retiro', ", "'Consignacion', ", "'Transferencia', "])
        seq += tipoop
        seq += str(random.randint(-10000000, 10000000)) + ", "

        start_date = datetime.date(1999, 1, 1)
        end_date = datetime.date(2021, 12, 3)
        time_between_dates = end_date - start_date
        days_between_dates = time_between_dates.days
        random_number_of_days = random.randrange(days_between_dates)
        random_date = start_date + \
            datetime.timedelta(days=random_number_of_days)
        seq += "TO_TIMESTAMP('" + random_date.strftime("%Y-%m-%d %H:%M:%S.%f") + \
            "', 'YYYY-MM-DD HH24:MI:SS.FF'), "

        if tipoop == "'Retiro', ":
            opciones = ["Oficina", "ATM"]
            ids = puntosA[random.choice(opciones)]
            ide = random.choice(ids)
            seq += ide + ", "

        elif tipoop == "'Consignacion', ":
            opciones = ["Oficina", "ATM"]
            ids = puntosA[random.choice(opciones)]
            ide = random.choice(ids)
            seq += ide + ", "
        else:
            opciones = ["Oficina", "ATM", "Web", "Movil"]
            ids = puntosA[random.choice(opciones)]
            ide = random.choice(ids)
            seq += ide + ", "

        cuentasaa = cuentasConUsuarios.keys()
        idCuenta = random.choice(tuple(cuentasaa))
        usuario = cuentasConUsuarios[idCuenta]
        seq += usuario + ", "
        seq += idCuenta + ", "

        if random.randint(1, 3) != 1:
            seq += "null, null, null"
        else:
            cuentaDestino = random.choice(tuple(cuentasConUsuarios.keys()))
            seq += cuentaDestino + ", "
            seq += random.choice(idCajeros)
        file.write(seq + ");\n")
        tuplasCreadas += 1
        if tuplasCreadas % 500 == 0:
            print("Operaciones: " + str(tuplasCreadas))

    file.close()
    print("Operaciones generadas")
    print(str(tuplasCreadas))
    t_f = process_time()
    print('Procesado en: ' + str(t_f - t_i) + 's')


def unificar():
    t_i = process_time()
    tuplasCreadas = 0
    filenames = ["b_usuario.sql", "b_empleado.sql", "b_oficina.sql", "b_puestoatencion.sql",
                 "b_producto.sql", "b_cuenta.sql", "b_prestamo.sql", "b_cuentajuridica.sql", "b_operacion.sql"]
    with open('bancandes.sql', 'w', encoding="utf-8") as outfile:
        for fname in filenames:
            with open(fname, encoding="utf-8") as infile:
                for line in infile:
                    outfile.write(line)
                    tuplasCreadas += 1
    print(str(tuplasCreadas))
    t_f = process_time()
    print('Uniificado en: ' + str(t_f - t_i) + 's')


# palabras()
# nombres()
# apellidos()

usuarios()
empleados()
oficinas()
pAtencion()
productos()
cuentas()
prestamos()
b_cuentasJuridicas()
operaciones()
print("Generados")
unificar()
