package com.example.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				//authorize許可する範囲の設定
				.authorizeHttpRequests(authz -> authz
						//urlログインは誰でも接続許可
						.requestMatchers("/login").permitAll()
						//他のリクエストは許可されてないとダメ
						.anyRequest().authenticated())
				//ログインフォームの設定
				.formLogin(form -> form
						//ログインフォームのURL指定
						.loginPage("/login")
						//authenticationにユーザーネームと
						//パスワードがPOSTされた時
						.loginProcessingUrl("/authentication")
						.usernameParameter("usernameInput")
						.passwordParameter("passwordInput")
						//ログイン成功なら下のURLへ
						.defaultSuccessUrl("/students")
						//失敗ならerrorパラムを持たせて/loginへ
						.failureUrl("/login?error"))
				//ログアウトとその後の処理に関する設定
				.logout(logout -> logout
						//以下のURLでアクセスがあったらログアウト
						.logoutUrl("/logout")
						//成功ならlogoutパラムを持たせて/loginへ
				        .logoutSuccessUrl("/login?logout")
				        //invalidate 意味:無効にする
				        //HTTPセッションを無効にする
						.invalidateHttpSession(true)
						//以下に設定したcookie(ログイン状態を管理する識別子)を削除
						.deleteCookies("JSESSIONID"));
		//SecurityFilterChain型で返すよ
		//streamでいうcollectに似てる
		return http.build();

	}
}
