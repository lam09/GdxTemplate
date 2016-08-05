 #version 120 
                    #ifdef GL_ES
                        precision mediump float; 
                    #endif   
                    varying vec4 v_color; 
                    varying vec2 v_texCoords; 
                    uniform sampler2D u_texture; 
                    uniform mat4 u_projTrans; 
                    void main() { 
                            vec4 color = texture2D(u_texture, v_texCoords).rgba; 
                          //  color.rgb -= vec3(0.45,0.45,0.45);     
                                          color.rgb -= vec3(0.0,0.45,0.45); 
                            gl_FragColor = color; 
                    };