package com.example.vinicius.condominium.infra.api

import com.example.vinicius.condominium.infra.api.endpoints.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

public class APIService{

    val BASE_URL = "http://api-condomais.herokuapp.com/api/v1/"

    private lateinit var retrofit:Retrofit
    private lateinit var interceptorAPI: InterceptorAPI

    lateinit var loginEndPoint: LoginEndPoint
    lateinit var postEndPoint: PostEndPoint
    lateinit var avisoEndPoint: AvisoEndPoint
    lateinit var entradaEndPoint: EntradaEndPoint
    lateinit var ocorrenciaEndPoint: OcorrenciaEndPoint
    lateinit var visitanteEndPoint: VisitanteEndPoint

    constructor(Token: String){

        interceptorAPI = InterceptorAPI("Token " + Token)

        val builderUsuario = OkHttpClient.Builder()
        builderUsuario.addInterceptor(interceptorAPI)
        val usuario = builderUsuario.build()

        val builderRetrofit = Retrofit.Builder()
        retrofit = builderRetrofit
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(usuario)
                .build()

        loginEndPoint = this.retrofit.create(LoginEndPoint::class.java)
        postEndPoint = this.retrofit.create(PostEndPoint::class.java)
        avisoEndPoint = this.retrofit.create(AvisoEndPoint::class.java)
        entradaEndPoint = this.retrofit.create(EntradaEndPoint::class.java)
        ocorrenciaEndPoint = this.retrofit.create(OcorrenciaEndPoint::class.java)
        visitanteEndPoint = this.retrofit.create(VisitanteEndPoint::class.java)

    }
}