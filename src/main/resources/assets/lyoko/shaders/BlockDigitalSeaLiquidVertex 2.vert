varying vec2 uv1;
varying vec2 uv2;
varying vec3 normal;
varying vec3 position;
varying vec3 lightVector;
varying vec3 tangentLightVector;
varying vec3 tangentPosVector;

void main(void) {
    
	vec3 lightPos = gl_LightSource[0].position.xyz - position;
	vec3 lightPos2 = vec3(gl_ModelViewMatrix * vec4(lightPos, 1.0));
	uv1 = gl_MultiTexCoord0.xy;
	uv2 = gl_MultiTexCoord1.xy;
	normal = gl_NormalMatrix * gl_Normal; 
 	position = vec3(gl_ModelViewMatrix * gl_Vertex);
	
	gl_Position = ftransform();

	vec3 tangent;
	vec3 v1 = cross(gl_Normal, vec3(0.0, 0.0, 1.0));
	vec3 v2 = cross(gl_Normal, vec3(0.0, 1.0, 0.0));
	if (length(v1) > length(v2)) {
		tangent = v1;
	} else {
		tangent = v2;
	}

	vec3 normalizedNormal = normalize(normal);
	vec3 translatedTangent = normalize(gl_NormalMatrix*tangent);
	vec3 biNormal = cross(normalizedNormal, translatedTangent);
	mat3 tangentMatrix =  mat3(translatedTangent, biNormal, normalizedNormal);

	vec3 translatedLightPos = normalize(lightPos2-position);
	tangentLightVector = tangentMatrix * lightVector;

	vec3 normalizedPosition = normalize(-position);
	tangentPosVector = tangentMatrix * normalizedPosition;
	
}