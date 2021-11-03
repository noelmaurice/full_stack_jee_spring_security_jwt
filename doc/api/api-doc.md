```json
{
  "openapi": "3.0.3",
  "info": {
    "title": "Api Documentation",
    "description": "Api Documentation",
    "termsOfService": "urn:tos",
    "contact": {},
    "license": {
      "name": "Apache 2.0",
      "url": "http://www.apache.org/licenses/LICENSE-2.0"
    },
    "version": "1.0"
  },
  "servers": [
    {
      "url": "http://localhost:8080",
      "description": "Inferred Url"
    }
  ],
  "tags": [
    {
      "name": "AuthController",
      "description": "Authentication services"
    },
    {
      "name": "ContentController",
      "description": "Content provider"
    },
    {
      "name": "RoleController",
      "description": "Role management"
    },
    {
      "name": "UserController",
      "description": "User management"
    }
  ],
  "paths": {
    "/api/auth/signin": {
      "post": {
        "tags": [
          "auth-controller"
        ],
        "summary": "authenticateUser",
        "operationId": "authenticateUserUsingPOST",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/LoginRequest"
              }
            }
          }
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/JwtResponse"
                }
              }
            }
          },
          "201": {
            "description": "Created"
          },
          "401": {
            "description": "Unauthorized"
          },
          "403": {
            "description": "Forbidden"
          },
          "404": {
            "description": "Not Found"
          }
        }
      }
    },
    "/api/auth/signup": {
      "post": {
        "tags": [
          "auth-controller"
        ],
        "summary": "registerUser",
        "operationId": "registerUserUsingPOST",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/SignupRequest"
              }
            }
          }
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/MessageResponse"
                }
              }
            }
          },
          "201": {
            "description": "Created"
          },
          "401": {
            "description": "Unauthorized"
          },
          "403": {
            "description": "Forbidden"
          },
          "404": {
            "description": "Not Found"
          }
        }
      }
    },
    "/api/content/admin": {
      "get": {
        "tags": [
          "content-controller"
        ],
        "summary": "adminContent",
        "operationId": "adminContentUsingGET",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/MessageResponse"
                }
              }
            }
          },
          "401": {
            "description": "Unauthorized"
          },
          "403": {
            "description": "Forbidden"
          },
          "404": {
            "description": "Not Found"
          }
        }
      }
    },
    "/api/content/mod": {
      "get": {
        "tags": [
          "content-controller"
        ],
        "summary": "moderatorContent",
        "operationId": "moderatorContentUsingGET",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/MessageResponse"
                }
              }
            }
          },
          "401": {
            "description": "Unauthorized"
          },
          "403": {
            "description": "Forbidden"
          },
          "404": {
            "description": "Not Found"
          }
        }
      }
    },
    "/api/content/public": {
      "get": {
        "tags": [
          "content-controller"
        ],
        "summary": "publicContent",
        "operationId": "publicContentUsingGET",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/MessageResponse"
                }
              }
            }
          },
          "401": {
            "description": "Unauthorized"
          },
          "403": {
            "description": "Forbidden"
          },
          "404": {
            "description": "Not Found"
          }
        }
      }
    },
    "/api/content/user": {
      "get": {
        "tags": [
          "content-controller"
        ],
        "summary": "userContent",
        "operationId": "userContentUsingGET",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/MessageResponse"
                }
              }
            }
          },
          "401": {
            "description": "Unauthorized"
          },
          "403": {
            "description": "Forbidden"
          },
          "404": {
            "description": "Not Found"
          }
        }
      }
    },
    "/api/role/all": {
      "get": {
        "tags": [
          "role-controller"
        ],
        "summary": "getAll",
        "operationId": "getAllUsingGET",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/Role"
                  }
                }
              }
            }
          },
          "401": {
            "description": "Unauthorized"
          },
          "403": {
            "description": "Forbidden"
          },
          "404": {
            "description": "Not Found"
          }
        }
      }
    },
    "/api/user/": {
      "get": {
        "tags": [
          "user-controller"
        ],
        "summary": "getByUsername",
        "operationId": "getByUsernameUsingGET",
        "parameters": [
          {
            "name": "username",
            "in": "query",
            "description": "username",
            "required": true,
            "style": "form",
            "schema": {
              "type": "string"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/User"
                }
              }
            }
          },
          "401": {
            "description": "Unauthorized"
          },
          "403": {
            "description": "Forbidden"
          },
          "404": {
            "description": "Not Found"
          }
        }
      }
    },
    "/api/user/all": {
      "get": {
        "tags": [
          "user-controller"
        ],
        "summary": "getAll",
        "operationId": "getAllUsingGET_1",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/User"
                  }
                }
              }
            }
          },
          "401": {
            "description": "Unauthorized"
          },
          "403": {
            "description": "Forbidden"
          },
          "404": {
            "description": "Not Found"
          }
        }
      }
    },
    "/api/user/all/rolename": {
      "get": {
        "tags": [
          "user-controller"
        ],
        "summary": "getAllByRoleName",
        "operationId": "getAllByRoleNameUsingGET",
        "parameters": [
          {
            "name": "rolename",
            "in": "query",
            "description": "rolename",
            "required": true,
            "style": "form",
            "schema": {
              "type": "string",
              "enum": [
                "ROLE_ADMIN",
                "ROLE_MOD",
                "ROLE_USER"
              ]
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/User"
                  }
                }
              }
            }
          },
          "401": {
            "description": "Unauthorized"
          },
          "403": {
            "description": "Forbidden"
          },
          "404": {
            "description": "Not Found"
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "JwtResponse": {
        "title": "JwtResponse",
        "type": "object",
        "properties": {
          "accessToken": {
            "type": "string"
          },
          "email": {
            "type": "string"
          },
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "roles": {
            "type": "array",
            "items": {
              "type": "string"
            }
          },
          "tokenType": {
            "type": "string"
          },
          "username": {
            "type": "string"
          }
        }
      },
      "LoginRequest": {
        "title": "LoginRequest",
        "required": [
          "password",
          "username"
        ],
        "type": "object",
        "properties": {
          "password": {
            "type": "string"
          },
          "username": {
            "type": "string"
          }
        }
      },
      "MessageResponse": {
        "title": "MessageResponse",
        "type": "object",
        "properties": {
          "message": {
            "type": "string"
          }
        }
      },
      "Role": {
        "title": "Role",
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int32"
          },
          "name": {
            "type": "string",
            "enum": [
              "ROLE_ADMIN",
              "ROLE_MOD",
              "ROLE_USER"
            ]
          }
        }
      },
      "SignupRequest": {
        "title": "SignupRequest",
        "required": [
          "email",
          "password",
          "username"
        ],
        "type": "object",
        "properties": {
          "email": {
            "maxLength": 50,
            "minLength": 0,
            "type": "string"
          },
          "password": {
            "maxLength": 2147483647,
            "minLength": 3,
            "type": "string"
          },
          "roles": {
            "uniqueItems": true,
            "type": "array",
            "items": {
              "type": "string"
            }
          },
          "username": {
            "maxLength": 20,
            "minLength": 3,
            "type": "string"
          }
        }
      },
      "User": {
        "title": "User",
        "required": [
          "email",
          "password",
          "username"
        ],
        "type": "object",
        "properties": {
          "email": {
            "maxLength": 50,
            "minLength": 0,
            "type": "string"
          },
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "password": {
            "maxLength": 2147483647,
            "minLength": 3,
            "type": "string"
          },
          "roles": {
            "uniqueItems": true,
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/Role"
            }
          },
          "username": {
            "maxLength": 20,
            "minLength": 3,
            "type": "string"
          }
        }
      }
    }
  }
}
```