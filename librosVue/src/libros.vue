<template>
    <div>
        <div id="carousel">
            <carousel></carousel>
        </div>
        <div class="row">
            <div id="btn" class="col-md-8">
                <div class="row" style="padding: 1rem;">
                    <div class="col-md-2 mx-auto">
                        <button>Ordenar por autor</button>
                    </div>
                    <div class="col-md-2 mx-auto">
                        <button>Ordenar por autor</button>
                    </div>
                    <div class="col-md-2 mx-auto">
                        <button>Ordenar por autor</button>
                    </div>
                </div>
                <div class="container" style="padding-top: 2rem; padding: 2rem;">
                    <div class="row">


                        <div class="col-md-12">
                            <card></card>
                        </div>
                    </div>
                </div>
            </div>
            <div id="drag" class="col-md-4" style="padding: 1rem;">
                <div>
                    <div class="row">
                        <div>
                            <b-button v-b-modal.modal-1 class="btn btn-success float-right" size="sm">Agregar
                            Nueva</b-button>
                            <b-modal id="modal-1" title="Agregar Pelicula" hide-footer="true">
                                <b-form @submit="onSubmit" @reset="onReset" >

                                    <b-form-group id="input-group-name" label="Nombre:" label-for="input-name">
                                        <b-form-input id="input-name" v-model="libro.nombre" type="text"
                                            required></b-form-input>
                                    </b-form-group>

                                    <b-form-group id="input-group-autor" label="Autor:"
                                        label-for="input-autor">
                                        <b-form-input id="input-autor" v-model="libro.autor"
                                            required></b-form-input>
                                    </b-form-group>                                    

                                    <b-form-group id="input-group-fecha" label="Fecha de publicación:"
                                        label-for="input-fecha">
                                        <b-form-input id="input-fecha" v-model="libro.fecha" type="date"
                                            required></b-form-input>
                                    </b-form-group>

                                    <div class="col-5 mx-auto container mt-3">
                                        <b-button type="submit" variant="success">Aceptar</b-button>
                                        <b-button type="reset" variant="danger"
                                            style="margin-left: 0.5rem;">Borrar</b-button>
                                    </div>
                                </b-form>
                            </b-modal>
                        </div>
                        <div>
                            <h1>drag</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import card from './components/card.vue'
import carousel from './components/carouselPelis.vue';

export default {
    data() {
        return {
            items: ['Item 1', 'Item 2', 'Item 3'],
            newItem: '',
            dragIndex: null,
            libro: {
                "nombre": "",
                "fecha": "",
                "autor": ""
            }
        }
    },
    mounted: {

    },
    methods: {
        addItem() {
            if (this.newItem.trim() !== '') {
                this.items.push(this.newItem.trim());
                this.newItem = '';
            }
        },
        removeItem(index) {
            this.items.splice(index, 1);
        },
        onDragStart(index) {
            this.dragIndex = index;
        },
        onDragOver(index) {
            event.preventDefault();
        },
        onDrop(index) {
            if (this.dragIndex !== null) {
                const draggedItem = this.items[this.dragIndex];
                this.items.splice(this.dragIndex, 1);
                this.items.splice(index, 0, draggedItem);
                this.dragIndex = null;
            }
        },
        onDragEnd() {
            this.dragIndex = null;
        },
        onReset(e) {
            e.preventDefault();          
            this.libro.nombre = '';
            this.libro.autor = '';
            this.libro.fecha = '';
        },
        onSubmit(event) {
            const formData = new FormData();
            formData.append("nombre", this.libro.anio);
            formData.append("autor", this.libro.autor);
            formData.append("fecha", this.libro.fecha);
            console.log(formData);
            axios.post('http://localhost:8080/api/libros', formData, {
                
            })
                .then(response => {
                    mounted();
                })
                .catch(error => {
                    console.error('Error en el registro:', error);
                });
        },
    },
    computed: {
        listOne() {
            return this.items.filter((item) => item.list === 1);
        },
        listTwo() {
            return this.items.filter((item) => item.list === 2);
        },
    },
    components: {
        card,
        carousel
    }
}
</script>

<style>
.draggable-item {
    margin: 10px;
    padding: 10px;
    background-color: #f0f0f0;
    border: 1px solid #ccc;
    cursor: grab;
}

.drop-zone {
    background-color: #eee;
    margin-bottom: 10px;
    padding: 10px;
}

.drag-el {
    background-color: #fff;
    margin-bottom: 10px;
    padding: 5px;
}

.zoom-enter-active,
.zoom-leave-active {
    transition: transform 0.5s ease-in-out;
}

.zoom-enter,
.zoom-leave-to {
    transform: scale(0);
}

.hidden {
    display: none;
}</style>