CREATE table Cars(
    id int primary key GENERATED by default as identity,
    img varchar(100) NOT NULL,
    name varchar(30) NOT NULL,
    description varchar
)

insert into cars(img, name, description) values('/pictures/ŠKODA_OCTAVIA.png', 'ŠKODA OCTAVIA',
    'ŠKODA OCTAVIA - компактный автомобиль, производимый чешской компанией ŠKODA Auto. Он известен своей надежностью, комфортом и хорошими характеристиками безопасности. Модель доступна в различных вариантах, включая бензиновые и дизельные двигатели, а также с передним или полным приводом. OCTAVIA предлагает широкий выбор комплектаций, включая универсалы и лифтбеки, что делает его универсальным выбором для семейного использования или бизнеса.')

insert into cars(img, name, description) values('/pictures/SMART_FORTWO.png', 'SMART FORTWO',
    'SMART FORTWO - компактный городской автомобиль, предлагающий высокую маневренность и экономичность. Его короткая колесная база и низкий центр тяжести обеспечивают легкость в управлении и удобство парковки в узких местах. Элегантный дизайн и современные технологии делают его привлекательным выбором для тех, кто ценит комфорт и стиль.')

insert into cars(img, name, description) values('/pictures/KIA_RIO.png', 'KIA RIO',
    'KIA RIO - компактный городской автомобиль, отличающийся надежностью, экономичностью и простотой в управлении. Его короткая колесная база и низкий центр тяжести обеспечивают легкость маневрирования и удобство парковки в узких местах. Современный дизайн и богатые функции безопасности делают его привлекательным выбором для тех, кто ценит комфорт и безопасность на дороге.');

insert into cars(img, name, description) values('/pictures/HAVAL_JOLION.png', 'HAVAL JOLION',
                                                'HAVAL JOLION - современный SUV с мощным двигателем и высоким уровнем комфорта. Его внушительные размеры и дизайн подчеркивают статус и уверенность владельца. Спортивные характеристики и технологии безопасности делают его идеальным выбором для активного образа жизни и долгих поездок.');

insert into cars(img, name, description) values('/pictures/GEELY_COOLRAY.png', 'GEELY COOLRAY',
                                                'GEELY COOLRAY - компактный городской автомобиль с элегантным дизайном и экономичным двигателем. Он предлагает высокую маневренность и удобство парковки благодаря короткой колесной базе. Современные технологии и функции безопасности делают его привлекательным выбором для тех, кто ценит комфорт и эффективность в городских условиях.');

insert into cars(img, name, description) values('/pictures/HAVAL_F7X.png', 'HAVAL F7X',
                                                'HAVAL F7X - спортивный SUV с мощным двигателем и агрессивным дизайном. Он предлагает высокую производительность, комфорт и технологии безопасности, делая его идеальным для активного образа жизни и долгих поездок. Внушительный внешний вид и впечатляющие характеристики делают его заметным на дороге и среди конкурентов.');

insert into cars(img, name, description) values('/pictures/BMW_320I.png', 'BMW 320I',
                                                'BMW 320I - премиальный седан с мощным бензиновым двигателем и продвинутыми технологиями. Он предлагает исключительный комфорт, точное управление и высокую безопасность, делая его идеальным выбором для тех, кто ценит качество и стиль. Агрессивный дизайн и современные функции делают его заметным на дороге и среди конкурентов.');

insert into cars(img, name, description) values('/pictures/MERCEDES_GLS_250.png', 'MERCEDES GLS 250',
                                                'MERCEDES GLS 250 - роскошный SUV с мощным турбодизелем и продвинутыми технологиями. Он предлагает исключительный комфорт, безопасность и высокую производительность, делая его идеальным для долгих поездок и активного образа жизни. Агрессивный дизайн и премиальные материалы подчеркивают статус и уверенность владельца, делая его заметным на дороге и среди конкурентов.');

insert into cars(img, name, description) values('/pictures/AUDI_A5_45_TFSI_COUPE.png', 'AUDI A5 45 TFSI COUPE',
                                                'MERCEDES GLS 250 - роскошный SUV с мощным турбодизелем и продвинутыми технологиями. Он предлагает исключительный комфорт, безопасность и высокую производительность, делая его идеальным для долгих поездок и активного образа жизни. Агрессивный дизайн и премиальные материалы подчеркивают статус и уверенность владельца, делая его заметным на дороге и среди конкурентов.');

insert into cars(img, name, description) values('/pictures/INFINITI_QX50.png', 'INFINITI QX50',
                                                'Mercedes INFINITI QX50 - премиальный SUV с инновационным дизайном и высокими стандартами комфорта. Он предлагает мощный двигатель, продвинутые технологии безопасности и удобства, делая его идеальным для тех, кто ценит качество и стиль. Агрессивный внешний вид и впечатляющие характеристики делают его заметным на дороге и среди конкурентов, подчеркивая статус и уверенность владельца.');
