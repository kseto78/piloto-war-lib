package es.mpr.plataformamensajeria.util;

import com.map.j2ee.base.BaseVO;
import com.map.j2ee.dao.DAOException;
import com.map.j2ee.dao.ldap.LdapObjectClass;
import com.map.j2ee.dao.ldap.LdapUserType;
import com.map.j2ee.security.perm.dao.UserDAO;
import com.map.j2ee.security.perm.model.*;
import com.map.j2ee.security.perm.dao.ldap.*;
import java.util.*;
import javax.naming.directory.InitialDirContext;
import org.apache.log4j.Logger;

// Referenced classes of package com.map.j2ee.security.perm.dao.ldap:
//            LdapUserDAO

public class LdapUserDaoInercya extends LdapUserDAO
    implements UserDAO
{
	
    public static final String NAME_ATTRIBUTE_GROUP = "grupo";
    public static final String NAME_ATTRIBUTE_PWD = "userPassword";
    public static final String NAME_ATTRIBUTE_LOGIN = "userName";
    public static final String NAME_ATTRIBUTE_UID = "uid";
    private List ldapAttributes;

    public LdapUserDaoInercya()
    {
        initialize();
    }

    private void initialize()
    {
        ArrayList listobjClasses = new ArrayList();
        LdapObjectClass objClass = new LdapObjectClass();
        objClass.setName("usuario");
        objClass.setMandatory(Boolean.TRUE);
        listobjClasses.add(objClass);
        setListObjectClasses(listobjClasses);
        ArrayList ldapAttributes = new ArrayList();
        ldapAttributes.add("sn");
        ldapAttributes.add("Telefono1");
        ldapAttributes.add("organismo");
        ldapAttributes.add("unidadOrganizacional");
        ldapAttributes.add("email");
        ldapAttributes.add("NIF");
        ldapAttributes.add("CIF");
        ldapAttributes.add("Telefono2");
        ldapAttributes.add("Movil");
        ldapAttributes.add("DireCalle");
        ldapAttributes.add("DireCiudad");
        ldapAttributes.add("Provincia");
        ldapAttributes.add("CodPostal");
        ldapAttributes.add("facsimileTelephoneNumber");
        ldapAttributes.add("despacho");
        ldapAttributes.add("givenName");
        ldapAttributes.add("PuntosContacto");
        ldapAttributes.add("email2");
        ldapAttributes.add("DireNum");
        ldapAttributes.add("DirePiso");
        ldapAttributes.add("DirePuerta");
        ldapAttributes.add("DireEscalera");
        ldapAttributes.add("Pais");
        ldapAttributes.add("Pregunta");
        ldapAttributes.add("Respuesta");
        ldapAttributes.add("MailNoticias");
        ldapAttributes.add("MailNovedades");
        ldapAttributes.add("MailContenidosInf");
        ldapAttributes.add("MailServicios");
        ldapAttributes.add("MailCampanas");
        ldapAttributes.add("Eliminado");
        ldapAttributes.add("denominacion");
        setLdapAttributes(ldapAttributes);
        setUseSpringInit(true);
    }

    protected void updateUserGroups(UserVO anUser, InitialDirContext ctx)
        throws DAOException
    {
        GroupVO obj = null;
        UserVO databaseUser = (UserVO)getObject(anUser, ctx);
        Map groupsToAdd = new HashMap();
        Map groupsToRemove = new HashMap();
        if(anUser.getGroups() == null || anUser.getGroups().isEmpty())
        {
            if(databaseUser.getGroups() != null && !databaseUser.getGroups().isEmpty())
            {
                groupsToRemove.put("grupo", getGroupsDNs(databaseUser.getGroups()));
            }
        } else
        if(databaseUser.getGroups() == null || databaseUser.getGroups().isEmpty())
        {
            groupsToAdd.put("grupo", getGroupsDNs(anUser.getGroups()));
        } else
        {
            Collection grpFromScreen = anUser.getGroups();
            Collection grpFromDB = databaseUser.getGroups();
            Collection colGroupsToAdd = new ArrayList();
            Collection colGroupsToRemove = new ArrayList();
            Iterator it = grpFromScreen.iterator();
            do
            {
                if(!it.hasNext())
                {
                    break;
                }
                obj = (GroupVO)it.next();
                if(!grpFromDB.contains(obj))
                {
                    colGroupsToAdd.add(obj.getId());
                }
            } while(true);
            if(colGroupsToAdd.size() > 0)
            {
                groupsToAdd.put("grupo", colGroupsToAdd);
            }
            Iterator it2 = grpFromDB.iterator();
            do
            {
                if(!it2.hasNext())
                {
                    break;
                }
                obj = (GroupVO)it2.next();
                if(!grpFromScreen.contains(obj))
                {
                    colGroupsToRemove.add(obj.getId());
                }
            } while(true);
            if(colGroupsToRemove.size() > 0)
            {
                groupsToRemove.put("grupo", colGroupsToRemove);
            }
        }
        if(!groupsToAdd.isEmpty() || !groupsToRemove.isEmpty())
        {
            if(!groupsToRemove.isEmpty())
            {
                String shortDn = getRemainingName(anUser.getId(), ctx);
                removeAttributes(shortDn, groupsToRemove, ctx);
            }
            if(!groupsToAdd.isEmpty())
            {
                String shortDn = getRemainingName(anUser.getId(), ctx);
                addAttributes(shortDn, groupsToAdd, ctx);
            }
        }
    }

    protected Map loadQueryCriteria(Object values)
        throws DAOException
    {
        HashMap criteria = new HashMap();
        try
        {
            if(values instanceof UserVO)
            {
                UserVO usuario = (UserVO)values;
                boolean flagUseName = usuario.getUsername() != null && !usuario.getUsername().equals("");
                criteria = loadQueryCriteria((UserVO)values, flagUseName);
            } else
            {
                throw new IllegalArgumentException("The class argument aClass must be a subclass of class com.map.j2ee.security.perm" +
".model.GroupVO, or this class itself."
);
            }
        }
        catch(Exception ex)
        {
            throw new DAOException(ex);
        }
        return criteria;
    }

    public Collection getObjects(BaseVO aCriteria, InitialDirContext ctx)
        throws DAOException
    {
        if(aCriteria instanceof GroupVO)
        {
            ArrayList resultado = new ArrayList();
            ArrayList keys = new ArrayList(getListUserTypes().keySet());
            Collections.sort(keys);
            Map criteria;
            for(Iterator it = keys.iterator(); it.hasNext(); resultado.addAll(getCollection(UserVO.class, criteria, ctx)))
            {
                LdapUserType userType = (LdapUserType)getListUserTypes().get(it.next());
                criteria = loadQueryCriteriaGroup((GroupVO)aCriteria, userType.getObjectBaseDn());
            }

            return resultado;
        } else
        {
            return super.getObjects(aCriteria, ctx);
        }
    }

    private HashMap loadQueryCriteriaGroup(GroupVO group, String rama)
        throws DAOException
    {
        HashMap criteria = new HashMap();
        criteria.put("basedn", rama);
        StringBuffer query = new StringBuffer("(&");
        if(group.getId() != null && !group.getId().equals(""))
        {
            query.append("(grupo=").append(group.getId()).append(")");
        }
        query.append(")");
        criteria.put("filter", query.toString());
        return criteria;
    }

    protected HashMap loadQueryCriteria(UserVO values, boolean useUserNameAsFilter)
    {
        HashMap criteria = new HashMap();
        if(values.getId() != null)
        {
            criteria.put("dn", values.getId());
        } else
        {
            if(values.getTipoUsuario() == null || "".equals(values.getTipoUsuario()))
            {
                throw new IllegalArgumentException("User Type or User ID not specified");
            }
            criteria.put("basedn", getObjectBaseDNByUserType(values.getTipoUsuario()));
            StringBuffer query = new StringBuffer("(&");
            if(useUserNameAsFilter && values.getUsername() != null && !values.getUsername().trim().equals(""))
            {
                query.append((new StringBuilder()).append(" (userName=").append(values.getUsername()).append(")").toString());
            }
            Iterator iterator = getListObjectClasses().iterator();
            do
            {
                if(!iterator.hasNext())
                {
                    break;
                }
                LdapObjectClass objClass = (LdapObjectClass)iterator.next();
                if(objClass.getMandatory().booleanValue())
                {
                    query.append((new StringBuilder()).append("(objectClass=").append(objClass.getName()).append(")").toString());
                }
            } while(true);
            Iterator iteratorAtt = getLdapAttributes().iterator();
            do
            {
                if(!iteratorAtt.hasNext())
                {
                    break;
                }
                String attributeName = (String)iteratorAtt.next();
                if(values.getAttribute(attributeName) != null && !values.getAttribute(attributeName).equals(""))
                {
                    query.append((new StringBuilder()).append(" (").append(attributeName).append("=").append(values.getAttribute(attributeName)).append(")").toString());
                }
            } while(true);
            if(getGroupUsersApp() != null)
            {
                query.append((new StringBuilder()).append(" (grupo=").append(getGroupUsersApp()).append(")").toString());
            }
            query.append(")");
            if(logger.isDebugEnabled())
            {
                logger.debug((new StringBuilder()).append("LDAP QUERY: ").append(query).toString());
            }
            criteria.put("filter", query.toString());
            criteria.put("searchScope", ONELEVEL_SCOPE);
        }
        return criteria;
    }

    protected void loadLdapDataToObject(Object objectToLoad, Map datasrc)
        throws DAOException
    {
        UserVO usuario = null;
        String campos[] = null;
        if(objectToLoad instanceof UserVO)
        {
            usuario = (UserVO)objectToLoad;
        } else
        {
            throw new IllegalArgumentException("The class argument aClass must be a subclass of class com.map.j2ee.security.perm" +
".model.UserVO, or this class itself."
);
        }
        usuario.setUsername((String)datasrc.get("cn"));
        usuario.setCn((String)datasrc.get("cn"));
        String psswd = new String("userPassword");
        usuario.setPassword(psswd);
        if(datasrc.containsKey("fulldn"))
        {
            usuario.setId((String)datasrc.get("fulldn"));
            usuario.setTipoUsuario(getUserTypeKeyFromObjectBaseDN(getBaseDn((String)datasrc.get("fulldn"))));
        }
        Iterator iterator = getLdapAttributes().iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            String attributeName = (String)iterator.next();
            if(datasrc.containsKey(attributeName))
            {
                usuario.setAttribute(attributeName, datasrc.get(attributeName));
            }
        } while(true);
        if(datasrc.containsKey("grupo"))
        {
            if(datasrc.get("grupo") instanceof String)
            {
                campos = new String[1];
                campos[0] = (String)datasrc.get("grupo");
            } else
            {
                campos = new String[((Object[])(Object[])datasrc.get("grupo")).length];
                for(int ivc = 0; ivc < ((Object[])(Object[])datasrc.get("grupo")).length; ivc++)
                {
                    campos[ivc] = ((Object[])(Object[])datasrc.get("grupo"))[ivc].toString();
                }

            }
            ArrayList grupos = new ArrayList();
            for(int i = 0; i < campos.length; i++)
            {
                String string = campos[i];
                GroupVO newGroup = new GroupVO();
                newGroup.setId(string);
                grupos.add(newGroup);
            }

            usuario.setGroups(grupos);
        }
    }

    protected Map objectToLdapRecordMap(Object objectToTransform, InitialDirContext ctx)
        throws DAOException
    {
        UserVO usuario = null;
        if(objectToTransform instanceof UserVO)
        {
            usuario = (UserVO)objectToTransform;
        } else
        {
            throw new IllegalArgumentException("The class argument aClass must be a subclass of class com.map.j2ee.security.perm" +
".model.UserVO, or this class itself."
);
        }
        if(usuario.getTipoUsuario() == null || "".equals(usuario.getTipoUsuario()))
        {
            throw new IllegalArgumentException("User type not specified");
        }
        HashMap map = new HashMap();
        Iterator iterator = getListObjectClasses().iterator();
        if(getListObjectClasses().size() > 0)
        {
            String clases[] = new String[getListObjectClasses().size()];
            for(int cont = 0; iterator.hasNext(); cont++)
            {
                LdapObjectClass objClass = (LdapObjectClass)iterator.next();
                clases[cont] = objClass.getName();
            }

            map.put("objectClass", clases);
        }
        String dn = (new StringBuilder()).append("cn=").append(usuario.getUsername()).append(",").append(getRemainingName(getObjectBaseDNByUserType(usuario.getTipoUsuario()), ctx)).toString();
        map.put("dn", dn);
        map.put("userName", usuario.getUsername());
        map.put("uid", usuario.getUsername());
        if(getGroupUsersApp() != null)
        {
            addGroupUsersApp(usuario, ctx);
        }
        GroupVO g;
        for(Iterator iterator2 = usuario.getGroups().iterator(); iterator2.hasNext(); map.put("grupo", g.getId()))
        {
            g = (GroupVO)iterator2.next();
        }

        if(usuario.getPassword() != null && !usuario.getPassword().equals(""))
        {
            map.put("userPassword", usuario.getPassword().getBytes());
        }
        map.put("cn", usuario.getCn());
        String attributeName;
        Object value;
        for(Iterator attIterator = getLdapAttributes().iterator(); attIterator.hasNext(); map.put(attributeName, value))
        {
            attributeName = (String)attIterator.next();
            value = usuario.getAttribute(attributeName);
        }

        return map;
    }

    public List getLdapAttributes()
    {
        return ldapAttributes;
    }

    public void setLdapAttributes(List ldapAttributes)
    {
        this.ldapAttributes = ldapAttributes;
    }

    protected String getName_Attribute_Group()
    {
        return "grupo";
    }

    private Collection wrappedList(Collection listaUsuarios)
    {
        ArrayList resultado = new ArrayList();
        for(Iterator iterator = listaUsuarios.iterator(); iterator.hasNext(); resultado.add(new User060VO((UserVO)iterator.next()))) { }
        return resultado;
    }

    public Object getObject(BaseVO anUser, InitialDirContext ctx)
        throws DAOException
    {
        User060VO resultado = null;
        UserVO vo = (UserVO)super.getObject(anUser, ctx);
        if(vo != null)
        {
            resultado = new User060VO(vo);
        }
        return resultado;
    }

    public Collection getCollection(Class aClass, Map aCriteria, String refsToload[], int startAtIndex, int count, String sortedBy, boolean isAscending, 
            InitialDirContext ctx)
        throws DAOException
    {
        return wrappedList(super.getCollection(aClass, aCriteria, refsToload, startAtIndex, count, sortedBy, isAscending, ctx));
    }

    public BaseVO create(BaseVO toCreate, InitialDirContext ctx)
        throws DAOException
    {
        User060VO resultado = null;
        UserVO vo = (UserVO)super.create(toCreate, ctx);
        if(vo != null)
        {
            resultado = new User060VO(vo);
        }
        return resultado;
    }

    public BaseVO update(BaseVO toUpdate, InitialDirContext ctx)
        throws DAOException
    {
        User060VO resultado = null;
        UserVO vo = (UserVO)super.update(toUpdate, ctx);
        if(vo != null)
        {
            resultado = new User060VO(vo);
        }
        return resultado;
    }
}
