

INSERT INTO `categoria` (`nombre`) VALUES
('snack'),
('bebida')
;

INSERT INTO `marca` (`nombre`) VALUES
('Saborina'),
('Deliciana'),
('NutriVibe'),
('FrutoDorado')
;

INSERT INTO `producto` (`nombre`, `stock`, `precio`, `imagen`, `descripcion`, `id_categoria`, `id_marca`) VALUES
('Chips Picantes', 100, 1.99, 'chips_picantes.jpg', 'Deliciosas papas fritas con un toque de picante', 1, 1),
('Galletas de Chocolate', 150, 2.49, 'galletas_chocolate.jpg', 'Galletas crujientes con trozos de chocolate', 1, 2),
('Refresco Naranja', 200, 0.99, 'refresco_naranja.jpg', 'Refresco con sabor a naranja natural', 2, 3),
('Juguito de Manzana', 120, 1.49, 'juguito_manzana.jpg', 'Jugo natural de manzana, refrescante y saludable', 2, 4),
('Palitos de Queso', 80, 1.79, 'palitos_queso.jpg', 'Crujientes palitos de pan rellenos de queso', 1, 1),
('Agua Mineral', 300, 0.89, 'agua_mineral.jpg', 'Botella de agua mineral sin gas', 2, 2),
('Barra de Cereal', 50, 2.29, 'barra_cereal.jpg', 'Barra energética de avena y frutas', 1, 3),
('Gaseosa Cola', 180, 1.29, 'gaseosa_cola.jpg', 'Refresco cola, sabor clásico', 2, 1),
('Chicles Mentolados', 200, 0.79, 'chicles_mentolados.jpg', 'Chicles con sabor refrescante a menta', 1, 4),
('Café Instantáneo', 60, 2.99, 'cafe_instantaneo.jpg', 'Café soluble para preparar rápidamente', 2, 3),
('Papas Fritas Clásicas', 150, 1.49, 'papas_fritas_classicas.jpg', 'Papas fritas crujientes con sal', 1, 2),
('Tetra de Leche', 100, 1.59, 'tetra_leche.jpg', 'Leche en formato Tetra Pak, ideal para el desayuno', 2, 4),
('Nuez Tostada', 50, 2.99, 'nuez_tostada.jpg', 'Nueces tostadas con sal', 1, 1),
('Limonada Natural', 120, 1.19, 'limonada_natural.jpg', 'Limonada fresca con sabor natural', 2, 2),
('Panchos', 90, 1.99, 'panchos.jpg', 'Salchichas de pollo con pan suave', 1, 3);

